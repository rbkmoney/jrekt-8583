package com.rbkmoney.jrekt8583.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionCode {
    GOODS_AND_SERVICES("00"),
    CASH_WITHDRAWAL("01"),
    DEBIT_ADJUSTMENT("02"),
    GOODS_AND_SERVICES_WITH_CASH_DISBURSEMENT("09"),
    ACCOUNT_FUNDING("10"),
    QUASI_CASH("11"),
    CASH_ADVANCE("17"),
    REFUND("20"),
    DEPOSIT("21"),
    CREDIT_ADJUSTMENT("22"),
    ORIGINAL_CREDIT("26"),
    AVAILABLE_FUNDS_INQUIRY("30"),
    BALANCE_INQUIRY("31"),
    DETOKENIZATION_REQUEST("33"),
    AUTHORIZATION_HISTORY("37"),
    CARDHOLDER_ACCOUNT_TRANSFER("40"),
    BILL_PAYMENT("50"),
    PIN_CHANGE("70"),
    PIN_ASSIGNMENT("71"),
    FULL_ACCOUNT_STATEMENT_REQUEST("72"),
    CHEQUE_BOOK_REQUEST("73"),
    PIN_TRIES_COUNTER_RESET("74");

    private final String code;
}
