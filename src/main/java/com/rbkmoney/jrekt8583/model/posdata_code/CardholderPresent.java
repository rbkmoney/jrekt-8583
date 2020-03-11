package com.rbkmoney.jrekt8583.model.posdata_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CardholderPresent {
    CARDHOLDER_PRESENT("0"),
    CARDHOLDER_NOT_PRESENT_UNSPECIFIED("1"),
    CARDHOLDER_NOT_PRESENT_MAIL_ORDER("2"),
    CARDHOLDER_NOT_PRESENT_TELEPHONE("3"),
    CARDHOLDER_NOT_PRESENT_STAND_IN_AUTHORIZATION_OR_RECURRING_PAYMENT("4"),
    CARDHOLDER_NOT_PRESENT_ELECTRONIC_ORDER("5"),
    UNKNOWN("9");

    private final String code;

    public static CardholderPresent fromCode(String code){
        return Arrays.stream(CardholderPresent.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Unknown code " + code));
    }
}
