package com.rbkmoney.jrekt8583.netty.pipeline;

import com.rbkmoney.jrekt8583.IsoField;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoValue;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

import static com.rbkmoney.jrekt8583.IsoField.*;

/**
 * ChannelHandler responsible for logging messages.
 * <p>
 * According to PCI DSS, sensitive cardholder data, like PAN and track data, should not be exposed. When running in secure mode, sensitive cardholder data will be printed masked. </p>
 */
@ChannelHandler.Sharable
public class IsoMessageLoggingHandler extends LoggingHandler {

    public static final IsoField[] DEFAULT_MASKED_FIELDS = {
            PAN_EXTENDED,
            TRACK_2_DATA,
            TRACK_3_DATA,
            TRACK_1_DATA
    };
    private static final char MASK_CHAR = '*';
    private static final char[] MASKED_VALUE = "***".toCharArray();

    private final boolean printSensitiveData;
    private final boolean printFieldDescriptions;
    private final IsoField[] maskedFields;

    public IsoMessageLoggingHandler(LogLevel level,
                                    boolean printSensitiveData,
                                    boolean printFieldDescriptions,
                                    IsoField... maskedFields) {
        super(level);
        this.printSensitiveData = printSensitiveData;
        this.printFieldDescriptions = printFieldDescriptions;
        this.maskedFields = (maskedFields != null && maskedFields.length > 0) ? maskedFields : DEFAULT_MASKED_FIELDS;
    }

    public IsoMessageLoggingHandler(LogLevel level) {
        this(level, true, true);
    }

    private static char[] maskPAN(String fullPan) {
        char[] maskedPan = fullPan.toCharArray();
        for (int i = 6; i < maskedPan.length - 4; i++) {
            maskedPan[i] = MASK_CHAR;
        }
        return maskedPan;
    }

    @Override
    protected String format(ChannelHandlerContext ctx, String eventName, Object arg) {
        if (arg instanceof IsoMessage) {
            return super.format(ctx, eventName, formatIsoMessage((IsoMessage) arg));
        } else {
            return super.format(ctx, eventName, arg);
        }
    }

    private String formatIsoMessage(IsoMessage m) {
        StringBuilder sb = new StringBuilder();
        if (printSensitiveData) {
            sb.append("Message: ").append(m.debugString()).append("\n");
        }
        sb.append("MTI: 0x").append(String.format("%04x", m.getType()));
        for (IsoField isoField : IsoField.values()) {
            if (m.hasField(isoField.getId())) {
                final IsoValue<Object> field = m.getField(isoField.getId());
                sb.append("\n  ").append(isoField.getId())
                        .append(": [");

                if (printFieldDescriptions) {
                    sb.append(isoField).append(':');
                }

                char[] formattedValue;
                if (printSensitiveData) {
                    formattedValue = field.toString().toCharArray();
                } else {
                    if (isoField == PAN) {
                        formattedValue = maskPAN(field.toString());
                    } else if (Arrays.binarySearch(maskedFields, isoField) >= 0) {
                        formattedValue = MASKED_VALUE;
                    } else {
                        formattedValue = field.toString().toCharArray();
                    }

                }
                sb.append(field.getType()).append('(').append(field.getLength())
                        .append(")] = '").append(formattedValue).append('\'');

            }
        }
        return sb.toString();
    }
}
