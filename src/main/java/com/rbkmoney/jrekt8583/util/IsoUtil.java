package com.rbkmoney.jrekt8583.util;

import com.rbkmoney.jrekt8583.IsoField;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoValue;

public class IsoUtil {

    public static String getStringFieldValue(IsoMessage isoMessage, IsoField isoField) {
        return isoField.getIsoType()
                .format(
                        isoMessage.getObjectValue(isoField.getId()).toString(),
                        isoField.getLength()
                );
    }

    public static <T> IsoValue<T> buildIsoValue(IsoField isoField, T value) {
        return buildIsoValue(isoField, value, isoField.getLength());
    }

    public static <T> IsoValue<T> buildIsoValue(IsoField isoField, T value, int length) {
        return isoField.getIsoType().value(value, length);
    }

}
