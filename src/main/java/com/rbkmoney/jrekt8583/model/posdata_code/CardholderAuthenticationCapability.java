package com.rbkmoney.jrekt8583.model.posdata_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardholderAuthenticationCapability {
    NO_ELECTRONIC_AUTHENTICATION("0"),
    PIN_VERIFICATION_POSSIBLE("1"),
    ELECTRONIC_SIGNATURE_ANALYSIS("2"),
    MOBILE_ACCEPTANCE_SOLUTION("3"),
    ELECTRONIC_AUTHENTICATION_INOPERATIVE("5"),
    UNKNOWN("9");

    private final String code;
}
