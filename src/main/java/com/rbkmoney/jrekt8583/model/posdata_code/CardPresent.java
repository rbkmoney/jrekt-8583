package com.rbkmoney.jrekt8583.model.posdata_code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardPresent {
    CARDHOLDER_NOT_PRESENT("0"),
    CARDHOLDER_PRESENT("1"),
    UNKNOWN("9");

    private final String value;
}
