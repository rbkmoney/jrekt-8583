package com.rbkmoney.jrekt8583.model.posdata_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardDataOutputCapability {
    UNKNOWN("0"),
    NONE("1"),
    MAGNETIC_STRIPE_WRITE("2"),
    SMART_CARD_WRITE("3");

    private final String value;
}
