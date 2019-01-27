package com.rbkmoney.jrekt8583.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class HeaderLengthDecoder extends ByteToMessageDecoder {

    private final int lengthHeaderLength;

    public HeaderLengthDecoder(int lengthHeaderLength) {
        this.lengthHeaderLength = lengthHeaderLength;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object decoded = decode(ctx, in);
        if (decoded != null) {
            out.add(decoded);
        }
    }

    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        int frameLength = getUnadjustedFrameLength(in, lengthHeaderLength);

        if (in.readableBytes() < frameLength) {
            return null;
        }

        // extract frame
        int index = in.readerIndex();
        ByteBuf frame = extractFrame(in, lengthHeaderLength, frameLength);
        in.readerIndex(index + (lengthHeaderLength + frameLength));
        return frame;
    }

    protected ByteBuf extractFrame(ByteBuf buffer, int index, int length) {
        return buffer.retainedSlice(index, length);
    }

    protected int getUnadjustedFrameLength(ByteBuf buf, int length) {
        byte[] lengthField = ByteBufUtil.getBytes(buf, 0, length);
        return Integer.valueOf(new String(lengthField));
    }
}
