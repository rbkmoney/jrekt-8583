package com.rbkmoney.jrekt8583.client;

import com.rbkmoney.jrekt8583.AbstractIso8583Connector;
import com.rbkmoney.jrekt8583.netty.pipeline.AfterConnectListener;
import com.rbkmoney.jrekt8583.netty.pipeline.Iso8583ChannelInitializer;
import com.rbkmoney.jrekt8583.netty.pipeline.ReconnectOnCloseListener;
import com.rbkmoney.jrekt8583.util.IsoUtil;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Promise;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.rbkmoney.jrekt8583.IsoField.SYSTEM_TRACE_AUDIT_NUMBER;

public class Iso8583Client<T extends IsoMessage> extends AbstractIso8583Connector<ClientConfiguration, Bootstrap, T> {

    public static final long DEFAULT_TIMEOUT = 60L;
    private ReconnectOnCloseListener reconnectOnCloseListener;
    private AfterConnectListener afterConnectListener = () -> {};

    public Iso8583Client(SocketAddress socketAddress, ClientConfiguration config, MessageFactory<T> isoMessageFactory) {
        super(config, isoMessageFactory);
        setSocketAddress(socketAddress);
    }

    public Iso8583Client(SocketAddress socketAddress, MessageFactory<T> isoMessageFactory) {
        this(socketAddress, ClientConfiguration.getDefault(), isoMessageFactory);
    }

    /**
     * Connects synchronously to remote address.
     *
     * @return Returns the {@link ChannelFuture} which will be notified when this
     * channel is closed.
     * @throws InterruptedException if connection process was interrupted
     * @see #setSocketAddress(SocketAddress)
     */
    public ChannelFuture connect() throws InterruptedException {
        final Channel channel = connectAsync().sync().channel();
        assert (channel != null) : "Channel must be set";
        setChannel(channel);
        return channel.closeFuture();
    }

    /**
     * Connect synchronously to  specified host and port.
     *
     * @param host A server host to connect to
     * @param port A server port to connect to
     * @return {@link ChannelFuture} which will be notified when connection is established.
     * @throws InterruptedException if connection process was interrupted
     */
    public ChannelFuture connect(String host, int port) throws InterruptedException {
        return connect(new InetSocketAddress(host, port));
    }

    /**
     * Connects synchronously to specified remote address.
     *
     * @param serverAddress A server address to connect to
     * @return {@link ChannelFuture} which will be notified when connection is established.
     * @throws InterruptedException if connection process was interrupted
     */
    public ChannelFuture connect(SocketAddress serverAddress) throws InterruptedException {
        setSocketAddress(serverAddress);
        return connect().sync();
    }

    /**
     * Connects asynchronously to remote address.
     *
     * @return Returns the {@link ChannelFuture} which will be notified when this
     * channel is active.
     */
    public ChannelFuture connectAsync() {
        logger.debug("Connecting to {}", getSocketAddress());
        final Bootstrap b = getBootstrap();
        reconnectOnCloseListener.requestReconnect();
        final ChannelFuture connectFuture = b.connect();
        connectFuture.addListeners(connFuture -> {
                    if (!connectFuture.isSuccess()) {
                        reconnectOnCloseListener.scheduleReconnect();
                        return;
                    }
                    Channel channel = connectFuture.channel();
                    logger.debug("Client is connected to {}", channel.remoteAddress());
                    setChannel(channel);
                    channel.closeFuture().addListener(reconnectOnCloseListener);
                },
                afterConnectListener
        );

        return connectFuture;
    }

    @Override
    protected Bootstrap createBootstrap() {
        final Bootstrap b = new Bootstrap();
        b.group(getBossEventLoopGroup())
                .channel(NioSocketChannel.class)
                .remoteAddress(getSocketAddress())

                .handler(new Iso8583ChannelInitializer<>(
                        getConfiguration(),
                        getConfigurer(),
                        getWorkerEventLoopGroup(),
                        getIsoMessageFactory(),
                        getMessageHandler()
                ));

        configureBootstrap(b);

        b.validate();

        reconnectOnCloseListener = new ReconnectOnCloseListener(this,
                getConfiguration().getReconnectInterval(),
                getBossEventLoopGroup()
        );

        return b;
    }

    public ChannelFuture disconnectAsync() {
        reconnectOnCloseListener.requestDisconnect();
        final Channel channel = getChannel();
        if (channel != null) {
            final SocketAddress socketAddress = getSocketAddress();
            logger.info("Closing connection to {}", socketAddress);
            return channel.close();
        } else {
            return null;
        }
    }

    public void disconnect() throws InterruptedException {
        final ChannelFuture disconnectFuture = disconnectAsync();
        if (disconnectFuture != null) {
            disconnectFuture.await();
        }
    }

    public Future<T> sendAsync(T message) {
        Channel channel = getChannel();
        if (channel == null) {
            throw new IllegalStateException("Channel is not opened");
        }
        if (!channel.isWritable()) {
            throw new IllegalStateException("Channel is not writable");
        }
        Promise<T> promise = channel.eventLoop().newPromise();
        if (message.hasField(SYSTEM_TRACE_AUDIT_NUMBER.getId())) {
            String traceId = IsoUtil.getStringFieldValue(message, SYSTEM_TRACE_AUDIT_NUMBER);
            getMessageHandler().putIfAbsentPromise(traceId, promise);
        }
        channel.writeAndFlush(message);
        return promise;
    }

    public T send(T message) throws InterruptedException, ExecutionException, TimeoutException {
        return send(message, DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    public T send(T message, long timeout, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        try {
            return sendAsync(message).get(timeout, timeUnit);
        } catch (TimeoutException e) {
            logger.error("TimeoutException when send message: {} e: ", message, e);
            getMessageHandler().removePromise(IsoUtil.getStringFieldValue(message, SYSTEM_TRACE_AUDIT_NUMBER));
            throw e;
        }
    }

    public boolean isConnected() {
        Channel channel = getChannel();
        return channel != null && channel.isActive();
    }

    public void addAfterConnectListener(AfterConnectListener afterConnectListener) {
        if (afterConnectListener == null) {
            throw new IllegalArgumentException("listener must not be null");
        }
        this.afterConnectListener = afterConnectListener;
    }
}
