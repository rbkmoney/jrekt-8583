package com.rbkmoney.jrekt8583.example.client;

import com.rbkmoney.jrekt8583.client.ClientConfiguration;
import com.rbkmoney.jrekt8583.client.Iso8583Client;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.impl.SimpleTraceGenerator;
import com.solab.iso8583.parse.ConfigParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
public class Iso8583ClientConfig {

    @Value("${iso8583.connection.host}")
    private String host;

    @Value("${iso8583.connection.port}")
    private int port;

    @Value("${iso8583.connection.idleTimeout}")
    private int idleTimeout;

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public Iso8583Client<IsoMessage> iso8583Client() throws IOException {
        SocketAddress socketAddress = new InetSocketAddress(host, port);

        final ClientConfiguration configuration = ClientConfiguration.newBuilder()
//                .addLoggingHandler()
                .idleTimeout(idleTimeout)
                .addLoggingHandler()
                .logSensitiveData(false)
//                .workerThreadsCount(4)
                .build();

        return new Iso8583Client<>(socketAddress, configuration, clientMessageFactory());
    }

    private MessageFactory<IsoMessage> clientMessageFactory() throws IOException {
        final MessageFactory<IsoMessage> messageFactory = ConfigParser.createDefault();
        messageFactory.setCharacterEncoding(StandardCharsets.US_ASCII.name());
        messageFactory.setUseBinaryMessages(false);
        messageFactory.setAssignDate(true);
        messageFactory.setTraceNumberGenerator(new SimpleTraceGenerator((int) (System
                .currentTimeMillis() % 1000000)));
        return messageFactory;
    }
}
