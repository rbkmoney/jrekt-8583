package com.rbkmoney.jrekt8583.server;

import com.rbkmoney.jrekt8583.ConnectorConfiguration;

public class ServerConfiguration extends ConnectorConfiguration {

    public ServerConfiguration(Builder builder) {
        super(builder);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static ServerConfiguration getDefault() {
        return newBuilder().build();
    }

    public static class Builder extends ConnectorConfiguration.Builder<Builder> {
        public ServerConfiguration build() {
            return new ServerConfiguration(this);
        }
    }
}
