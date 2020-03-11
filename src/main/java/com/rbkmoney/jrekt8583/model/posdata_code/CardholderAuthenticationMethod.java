package com.rbkmoney.jrekt8583.model.posdata_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardholderAuthenticationMethod {
    NOT_AUTHENTICATED("0"),
    PIN_VERIFIED_ON_LINE_OR_OFF_LINE("1"),
    ELECTRONIC_SIGNATURE_ANALYSIS("2"),
    MANUAL_SIGNATURE_VERIFICATION("5"),
    OTHER_MANUAL_VERIFICATION("6"),
    CD_CVM_NOT_AVAILABLE("7"),
    UNKNOWN("9"),
    TOKENIZATION_REQUEST("T");

    private final String value;
}
