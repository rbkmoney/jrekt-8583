package com.rbkmoney.jrekt8583;

import com.rbkmoney.jrekt8583.netty.pipeline.CompositeIsoMessageHandler;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AbstractIso8583ConnectorTest<M extends IsoMessage> {

    private AbstractIso8583Connector<ConnectorConfiguration, AbstractBootstrap, M> subject;

    @Mock
    private ConnectorConfiguration config;
    @Mock
    private MessageFactory<M> messageFactory;
    @Mock
    private IsoMessageListener<M> listener;

    private CompositeIsoMessageHandler<M> compositeIsoMessageHandler;
    @Mock
    private ChannelHandlerContext ctx;
    @Mock
    private M message;

    @BeforeEach
    public void setUp() {
        compositeIsoMessageHandler = new CompositeIsoMessageHandler<>();
        subject = new AbstractIso8583Connector<ConnectorConfiguration, AbstractBootstrap, M>(
                config, messageFactory, compositeIsoMessageHandler
        ) {
            @Override
            protected AbstractBootstrap createBootstrap() {
                throw new UnsupportedOperationException("Method is not implemented: .createBootstrap");
            }
        };
    }

    @Test
    public void addMessageListener() throws Exception {
        //given
        @SuppressWarnings("unchecked") IsoMessageListener<M> listener = mock(IsoMessageListener.class);
        when(listener.applies(message)).thenReturn(true);

        //when
        subject.addMessageListener(listener);
        compositeIsoMessageHandler.channelRead(ctx, message);

        // then
        verify(listener).onMessage(ctx, message);
    }

    @Test
    public void removeMessageListener() throws Exception {
        //given
        subject.addMessageListener(listener);
        @SuppressWarnings("unchecked") IsoMessageListener<M> listener = mock(IsoMessageListener.class);

        //when
        subject.removeMessageListener(listener);
        compositeIsoMessageHandler.channelRead(ctx, message);

        // then
        verifyZeroInteractions(listener);
    }

}