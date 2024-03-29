/*
 * Copyright 2014 The FIX.io Project
 *
 * The FIX.io Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.rbkmoney.jrekt8583.netty.pipeline;

import com.rbkmoney.jrekt8583.ConnectorConfiguration;
import com.rbkmoney.jrekt8583.ConnectorConfigurer;
import com.rbkmoney.jrekt8583.netty.codec.HeaderLengthDecoder;
import com.rbkmoney.jrekt8583.netty.codec.Iso8583Decoder;
import com.rbkmoney.jrekt8583.netty.codec.Iso8583Encoder;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.channel.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.timeout.IdleStateHandler;

@SuppressWarnings("WeakerAccess")
public class Iso8583ChannelInitializer<
        T extends Channel,
        B extends AbstractBootstrap,
        C extends ConnectorConfiguration> extends ChannelInitializer<T> {

    private final C configuration;
    private final ConnectorConfigurer<C, B> configurer;
    private final EventLoopGroup workerGroup;
    private final MessageFactory isoMessageFactory;
    private final ChannelHandler[] customChannelHandlers;
    private final Iso8583Encoder isoMessageEncoder;
    private final ChannelHandler loggingHandler;
    private final ChannelHandler parseExceptionHandler;
    private final IdleEventHandler idleEventHandler;

    public Iso8583ChannelInitializer(
            C configuration,
            ConnectorConfigurer<C, B> configurer,
            EventLoopGroup workerGroup,
            MessageFactory isoMessageFactory,
            ChannelHandler... customChannelHandlers) {
        this.configuration = configuration;
        this.configurer = configurer;
        this.workerGroup = workerGroup;
        this.isoMessageFactory = isoMessageFactory;
        this.customChannelHandlers = customChannelHandlers;

        this.isoMessageEncoder = createIso8583Encoder(configuration);
        this.loggingHandler = createLoggingHandler(configuration);
        this.parseExceptionHandler = createParseExceptionHandler();
        this.idleEventHandler = createIdleEventHandler(configuration);
    }

    @Override
    public void initChannel(T ch) {
        final ChannelPipeline pipeline = ch.pipeline();

        final int lengthFieldLength = configuration.getFrameLengthFieldLength();
        pipeline.addLast("headerLengthDecoder", new HeaderLengthDecoder(lengthFieldLength));

        pipeline.addLast("iso8583Decoder", createIso8583Decoder(isoMessageFactory));

        pipeline.addLast("iso8583Encoder", isoMessageEncoder);

        if (configuration.addLoggingHandler()) {
            pipeline.addLast(workerGroup, "logging", loggingHandler);
        }

        if (configuration.replyOnError()) {
            pipeline.addLast(workerGroup, "replyOnError", parseExceptionHandler);
        }

        pipeline.addLast("idleState", new IdleStateHandler(0, 0, configuration.getIdleTimeout()));
        pipeline.addLast("idleEventHandler", idleEventHandler);
        if (customChannelHandlers != null) {
            pipeline.addLast(workerGroup, customChannelHandlers);
        }

        if (configurer != null) {
            configurer.configurePipeline(pipeline, configuration);
        }
    }

    protected MessageFactory getIsoMessageFactory() {
        return isoMessageFactory;
    }

    protected ChannelHandler createParseExceptionHandler() {
        return new ParseExceptionHandler(isoMessageFactory, true);
    }

    protected Iso8583Encoder createIso8583Encoder(C configuration) {
        return new Iso8583Encoder(configuration.getFrameLengthFieldLength());
    }

    protected Iso8583Decoder createIso8583Decoder(final MessageFactory messageFactory) {
        return new Iso8583Decoder(messageFactory);
    }

    protected ChannelHandler createLoggingHandler(C configuration) {
        return new IsoMessageLoggingHandler(LogLevel.DEBUG,
                configuration.logSensitiveData(),
                configuration.logFieldDescription(),
                configuration.getSensitiveDataFields());
    }


    private IdleEventHandler createIdleEventHandler(C configuration) {
        if (configuration.getIdleEventHandler() != null) {
            return configuration.getIdleEventHandler();
        }
        return new IdleEventHandler(() -> isoMessageFactory.newMessage(0x800)) {
        };
    }

}
