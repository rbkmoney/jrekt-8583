package com.rbkmoney.jrekt8583.netty.pipeline;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public interface AfterConnectListener extends ChannelFutureListener {

    @Override
    default void operationComplete(ChannelFuture future) throws Exception {
        if (future.isSuccess()) {
            doAfterConnect();
        }
    }

    void doAfterConnect() throws Exception;
}
