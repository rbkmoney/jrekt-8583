package com.rbkmoney.jrekt8583.model.posdata_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardholderAuthenticationEntity {
    NOT_AUTHENTICATED("0"),
    SMART_CARD ("1"),
    AUTHORIZATION_AGENT("3"),
    BY_MERCHANT("4"),
    OTHER("5"),
    UNKNOWN("9");

    private final String code;
}
