package com.rbkmoney.jrekt8583.model;

import lombok.Builder;

@Builder
public class ProcessingCode {
    private TransactionCode transactionCode;
    private AccountType fromAccountType;
    private AccountType toAccountType;

    @Override
    public String toString() {
        return transactionCode.getValue() +
                fromAccountType.getValue() +
                toAccountType.getValue();
    }

    public static ProcessingCode buildWithPrimaryAccount(TransactionCode transactionCode) {
        return ProcessingCode.builder()
                .transactionCode(transactionCode)
                .fromAccountType(AccountType.PRIMARY_ACCOUNT)
                .toAccountType(AccountType.PRIMARY_ACCOUNT)
                .build();
    }

}
