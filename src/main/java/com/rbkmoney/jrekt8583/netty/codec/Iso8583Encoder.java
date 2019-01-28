package com.rbkmoney.jrekt8583.netty.codec;

import com.solab.iso8583.IsoMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@ChannelHandler.Sharable
public class Iso8583Encoder extends MessageToByteEncoder<IsoMessage> {

    private final int lengthHeaderLength;

    public Iso8583Encoder(int lengthHeaderLength) {
        this.lengthHeaderLength = lengthHeaderLength;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, IsoMessage isoMessage, ByteBuf out) {
        byte[] bytes = isoMessage.writeData();
        out.writeBytes(String.format("%0" + lengthHeaderLength + "d", bytes.length).getBytes());
        out.writeBytes(bytes);
    }
}
