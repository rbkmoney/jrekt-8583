package com.rbkmoney.jrekt8583.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum  AccountType {
    PRIMARY_ACCOUNT ("00"),
    SAVINGS_ACCOUNT("10"),
    CHEQUE_ACCOUNT("20"),
    CREDIT_CARD_ACCOUNT("30"),
    UNIVERSAL_ACCOUNT("40");

    private final String code;
}
