package com.rbkmoney.jrekt8583.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

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

        int actualLengthHeaderOffset = in.readerIndex();
        int frameLength = getUnadjustedFrameLength(in, actualLengthHeaderOffset, lengthHeaderLength);

        if (frameLength < 0) {
            throw new CorruptedFrameException("negative frame length field: " + frameLength);
        }

        if (in.readableBytes() < frameLength) {
            return null;
        }

        // extract frame
        int readerIndexWithHeaderLength = in.readerIndex() + lengthHeaderLength;
        ByteBuf frame = extractFrame(in, readerIndexWithHeaderLength, frameLength);
        in.readerIndex(readerIndexWithHeaderLength + frameLength);
        return frame;
    }

    protected ByteBuf extractFrame(ByteBuf buffer, int index, int length) {
        return buffer.retainedSlice(index, length);
    }

    protected int getUnadjustedFrameLength(ByteBuf buf, int offset, int length) {
        byte[] lengthField = ByteBufUtil.getBytes(buf, offset, length);
        return Integer.valueOf(new String(lengthField));
    }
}
