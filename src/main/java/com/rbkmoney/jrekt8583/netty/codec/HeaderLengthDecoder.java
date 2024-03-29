package com.rbkmoney.jrekt8583.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

import static io.netty.util.internal.MathUtil.isOutOfBounds;

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
        if (!in.isReadable()) {
            return null;
        }

        int actualLengthHeaderOffset = in.readerIndex();
        //can read header length? if not, return nothing
        int capacity = in.capacity();
        if (isOutOfBounds(actualLengthHeaderOffset, lengthHeaderLength, capacity)) {
            return null;
        }
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
        try {
            return Integer.valueOf(new String(lengthField));
        } catch (NumberFormatException ex) {
            throw new CorruptedFrameException("Corrupted length field", ex);
        }
    }
}
