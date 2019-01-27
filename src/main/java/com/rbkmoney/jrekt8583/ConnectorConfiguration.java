package com.rbkmoney.jrekt8583;

import com.rbkmoney.jrekt8583.netty.pipeline.CompositeIsoMessageHandler;
import com.rbkmoney.jrekt8583.netty.pipeline.EchoMessageListener;
import com.rbkmoney.jrekt8583.netty.pipeline.IsoMessageLoggingHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public abstract class ConnectorConfiguration {

    /**
     * Default read/write idle timeout in seconds (ping interval) = 30 sec.
     *
     * @see #getIdleTimeout()
     */
    private static final int DEFAULT_IDLE_TIMEOUT_SECONDS = 30;

    /**
     * Default {@link #frameLengthFieldLength}
     *
     * @see #getFrameLengthFieldLength()
     */
    private static final int DEFAULT_FRAME_LENGTH_FIELD_LENGTH = 6;

    private final boolean addEchoMessageListener;
    private int idleTimeout;
    private final int workerThreadsCount;
    private boolean replyOnError;
    private boolean addLoggingHandler;
    private boolean logSensitiveData;
    private int[] sensitiveDataFields;
    private boolean logFieldDescription;
    private final int frameLengthFieldLength;

    protected ConnectorConfiguration(final Builder builder) {
        addLoggingHandler = builder.addLoggingHandler;
        idleTimeout = builder.idleTimeout;
        logFieldDescription = builder.logFieldDescription;
        logSensitiveData = builder.logSensitiveData;
        replyOnError = builder.replyOnError;
        sensitiveDataFields = builder.sensitiveDataFields;
        addEchoMessageListener = builder.addEchoMessageListener;
        workerThreadsCount = builder.workerThreadsCount;
        frameLengthFieldLength = builder.frameLengthFieldLength;
    }

    /**
     * Allows to add default echo message listener to {@link AbstractIso8583Connector}.
     *
     * @return true if {@link EchoMessageListener} should be added to {@link CompositeIsoMessageHandler}
     */

    public boolean shouldAddEchoMessageListener() {
        return addEchoMessageListener;
    }

    /**
     * Channel read/write idle timeout in seconds.
     * <p>
     * If no message was received/sent during specified time interval then `Echo` message will be sent.</p>
     *
     * @return timeout in seconds
     */
    public int getIdleTimeout() {
        return idleTimeout;
    }

    /**
     * Returns true is {@link IsoMessageLoggingHandler}
     * <p>Allows to disable adding default logging handler to {@link ChannelPipeline}.</p>
     *
     * @return true if {@link IsoMessageLoggingHandler} should be added.
     */
    public boolean addLoggingHandler() {
        return addLoggingHandler;
    }

    /**
     * Whether to reply with administrative message in case of message syntax errors. Default value is <code>false.</code>
     *
     * @return true if reply message should be sent in case of error parsing the message.
     */
    public boolean replyOnError() {
        return replyOnError;
    }

    /**
     * Returns <code>true</code> if sensitive information like PAN, CVV/CVV2, and Track2 should be printed to log.
     * <p>
     * Default value is <code>false</code> (sensitive data is not printed).
     * </p>
     *
     * @return <code>true</code> if sensitive data should be printed to log
     */
    public boolean logSensitiveData() {
        return logSensitiveData;
    }

    public boolean logFieldDescription() {
        return logFieldDescription;
    }

    /**
     * Returns field numbers to be treated as sensitive data.
     * Use <code>null</code> to use default ones
     *
     * @return array of ISO8583 sensitive field numbers to be masked, or <code>null</code> to use default fields.
     * @see IsoMessageLoggingHandler
     * @see IsoMessageLoggingHandler#DEFAULT_MASKED_FIELDS
     */
    public int[] getSensitiveDataFields() {
        return sensitiveDataFields;
    }

    /**
     * Returns number of threads in worker {@link EventLoopGroup}.
     *
     * @implNote Default value is <code>Runtime.getRuntime().availableProcessors() * 16</code>
     */
    public int getWorkerThreadsCount() {
        return workerThreadsCount;
    }

    /**
     * Returns length of TCP frame length field.
     *
     * @implNote Default value is <code>2</code>
     * @see LengthFieldBasedFrameDecoder
     */
    public int getFrameLengthFieldLength() {
        return frameLengthFieldLength;
    }

    protected abstract static class Builder<B extends Builder> {
        private boolean addLoggingHandler = false;
        private boolean addEchoMessageListener = false;
        private boolean logFieldDescription = true;
        private boolean logSensitiveData = false;
        private boolean replyOnError = false;
        private int idleTimeout = DEFAULT_IDLE_TIMEOUT_SECONDS;
        private int workerThreadsCount = 0; // use netty default
        private int[] sensitiveDataFields;
        private int frameLengthFieldLength = DEFAULT_FRAME_LENGTH_FIELD_LENGTH;

        public B addEchoMessageListener() {
            this.addEchoMessageListener = true;
            return (B) this;
        }

        public B idleTimeout(int timeout) {
            this.idleTimeout = timeout;
            return (B) this;
        }

        public B replyOnError(boolean doReply) {
            this.replyOnError = doReply;
            return (B) this;
        }

        public B addLoggingHandler() {
            this.addLoggingHandler = true;
            return (B) this;
        }

        public B addLoggingHandler(boolean value) {
            this.addLoggingHandler = value;
            return (B) this;
        }

        /**
         * Should log sensitive data (unmasked) or not.
         * <p>
         * Don't use on production!
         */
        public B logSensitiveData(boolean logSensitiveData) {
            this.logSensitiveData = logSensitiveData;
            return (B) this;
        }

        public B describeFieldsInLog() {
            this.logFieldDescription = true;
            return (B) this;
        }

        public B sensitiveDataFields(int... sensitiveDataFields) {
            this.sensitiveDataFields = sensitiveDataFields;
            return (B) this;
        }

        public B frameLengthFieldLength(int frameLengthFieldLength) {
            this.frameLengthFieldLength = frameLengthFieldLength;
            return (B) this;
        }

        public B workerThreadsCount(int numberOfThreads) {
            this.workerThreadsCount = numberOfThreads;
            return (B) this;
        }
    }
}
