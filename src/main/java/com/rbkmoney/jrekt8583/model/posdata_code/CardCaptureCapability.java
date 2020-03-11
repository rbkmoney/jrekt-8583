package com.rbkmoney.jrekt8583.model.posdata_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardCaptureCapability {
    NONE("0"),
    CAPTURE("1"),
    UNKNOWN("9");

    private final String value;
}
