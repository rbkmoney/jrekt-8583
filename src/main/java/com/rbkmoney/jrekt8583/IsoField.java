package com.rbkmoney.jrekt8583;

import com.solab.iso8583.IsoType;

public enum IsoField {

    PAN(2, IsoType.LLVAR, 19),
    PROCESSING_CODE(3, IsoType.NUMERIC, 6),
    AMOUNT_TRANSACTION(4, IsoType.AMOUNT, 12),
    AMOUNT_SETTLEMENT(5, IsoType.NUMERIC, 12),
    AMOUNT_CARDHOLDER_BILLING(6, IsoType.NUMERIC, 12),
    TRANSMISSION_DATE_AND_TIME(7, IsoType.DATE14, 14),
    AMOUNT_CARDHOLDER_BILLING_FEE(8, IsoType.NUMERIC, 8),
    CONVERSION_RATE_SETTLEMENT(9, IsoType.NUMERIC, 8),
    CONVERSION_RATE_CARDHOLDER_BILLING(10, IsoType.NUMERIC, 8),
    SYSTEM_TRACE_AUDIT_NUMBER(11, IsoType.NUMERIC, 6),
    DATE_AND_TIME_LOCAL_TRANSACTION(12, IsoType.DATE14, 14),
    DATE_EFFECTIVE(13, IsoType.NUMERIC, 4),
    DATE_EXPIRATION(14, IsoType.DATE_EXP, 4),
    DATE_SETTLEMENT(15, IsoType.DATE14, 14),
    DATE_CONVERSION(16, IsoType.NUMERIC, 4),
    DATE_CAPTURE(17, IsoType.NUMERIC, 4),
    MERCHANTS_TYPE(18, IsoType.NUMERIC, 4),
    ACQUIRING_INSTITUTION_COUNTRY_CODE(19, IsoType.NUMERIC, 3),
    COUNTRY_CODE_PAN(20, IsoType.NUMERIC, 3),
    COUNTRY_CODE_FORWARDING_INSTITUTION(21, IsoType.NUMERIC, 3),
    POS_DATA_CODE(22, IsoType.NUMERIC, 12),
    CARD_SEQUENCE_NUMBER(23, IsoType.NUMERIC, 3),
    FUNCTION_CODE(24, IsoType.NUMERIC, 3),
    MESSAGE_REASON_CODE(25, IsoType.NUMERIC, 4),
    CARD_ACCEPTOR_BUSINESS_CODE(26, IsoType.NUMERIC, 4),
    APPROVAL_CODE_LENGTH(27, IsoType.NUMERIC, 1),
    DATE_RECONCILIATION(28, IsoType.AMOUNT, 14),
    RECONCILIATION_INDICATOR(29, IsoType.AMOUNT, 3),
    AMOUNT_ORIGINAL(30, IsoType.AMOUNT, 24),
    ACQUIRER_REFERENCE_DATA(31, IsoType.AMOUNT, 99),
    ACQUIRING_INSTITUTION_IDENT_CODE(32, IsoType.LLVAR, 11),
    FORWARDING_INSTITUTION_IDENT_CODE(33, IsoType.LLVAR, 11),
    PAN_EXTENDED(34, IsoType.LLVAR, 28),
    TRACK_2_DATA(35, IsoType.LLVAR, 37),
    TRACK_3_DATA(36, IsoType.LLLVAR, 104),
    RETRIEVAL_REFERENCE_NUMBER(37, IsoType.ALPHA, 12),
    APPROVAL_CODE(38, IsoType.ALPHA, 6),
    ACTION_CODE(39, IsoType.ALPHA, 3),
    SERVICE_RESTRICTION_CODE(40, IsoType.ALPHA, 3),
    CARD_ACCEPTOR_TERMINAL_IDENTIFICATION(41, IsoType.ALPHA, 8),
    CARD_ACCEPTOR_IDENTIFICATION_CODE(42, IsoType.ALPHA, 15),
    CARD_ACCEPTOR_NAME_LOCATION(43, IsoType.LLVAR, 99),
    ADITIONAL_RESPONSE_DATA(44, IsoType.LLVAR, 99),
    TRACK_1_DATA(45, IsoType.LLVAR, 76),
    AMOUNTS_FEES(46, IsoType.LLLVAR, 204),
    ADITIONAL_DATA_NATIONAL(47, IsoType.LLLVAR, 999),
    ADITIONAL_DATA_PRIVATE(48, IsoType.LLLVAR, 999),
    CURRENCY_CODE_TRANSACTION(49, IsoType.ALPHA, 3),
    CURRENCY_CODE_RECONCILIATION(50, IsoType.ALPHA, 3),
    CURRENCY_CODE_CARDHOLDER_BILLING(51, IsoType.ALPHA, 3),
    PIN_DATA(52, IsoType.NUMERIC, 8),
    SECURITY_RELATED_CONTROL_INFORMATION(53, IsoType.NUMERIC, 24),
    AMOUNTS_ADDITIONAL(54, IsoType.LLLVAR, 360),
    INTEGRATED_CARD_CIRCUIT_SYSTEM_RELATED_DATA(55, IsoType.LLLVAR, 510),
    ORIGINAL_DATA_ELEMENTS(56, IsoType.LLVAR, 37),
    AUTHORIZATION_LIFE_CYCLE_CODE(57, IsoType.LLLVAR, 3),
    AUTHORIZING_AGENT_INSTITUTION_IDENTIFICATION_CODE(58, IsoType.LLLVAR, 11),
    TRANSPORT_DATA(59, IsoType.LLLVAR, 999),
    RESERVED_FOR_NATIONAL_USE(60, IsoType.LLLVAR, 999),
    RESERVED_FOR_NATIONAL_USE_2(61, IsoType.LLLVAR, 999),
    RESERVED_FOR_PRIVATE_USE(62, IsoType.LLLVAR, 999),
    RESERVED_FOR_PRIVATE_USE_2(63, IsoType.LLLVAR, 999),
    MESSAGE_AUTHENTICATION_CODE_FIELD(64, IsoType.BINARY, 8),
    RESERVED_FOR_ISO_USE(65, IsoType.BINARY, 999),
    AMOUNTS_ORIGINAL_FEES(66, IsoType.NUMERIC, 204),
    EXTENDED_PAYMENT_CODE(67, IsoType.NUMERIC, 2),
    RECEIVING_INSTITUTION_COUNTRY_CODE(68, IsoType.NUMERIC, 3),
    SETTLEMENT_INSTITUTION_COUNTRY_CODE(69, IsoType.NUMERIC, 3),
    AUTHORIZING_AGENT_COUNTRY_CODE(70, IsoType.NUMERIC, 3),
    MESSAGE_NUMBER(71, IsoType.NUMERIC, 8),
    DATA_RECORD(72, IsoType.LLLVAR, 999),
    DATE_ACTION(73, IsoType.NUMERIC, 6),
    CREDITS_NUMBER(74, IsoType.NUMERIC, 10),
    CREDITS_REVERSAL_NUMBER(75, IsoType.NUMERIC, 10),
    DEBITS_NUMBER(76, IsoType.NUMERIC, 10),
    DEBITS_REVERSAL_NUMBER(77, IsoType.NUMERIC, 10),
    TRANSFER_NUMBER(78, IsoType.NUMERIC, 10),
    TRANSFER_REVERSAL_NUMBER(79, IsoType.NUMERIC, 10),
    INQUIRIES_NUMBER(80, IsoType.NUMERIC, 10),
    AUTHORIZATION_NUMBER(81, IsoType.NUMERIC, 10),
    INQUIRIES_REVERSAL_NUMBER(82, IsoType.NUMERIC, 10),
    PAYMENTS_NUMBER(83, IsoType.NUMERIC, 10),
    PAYMENTS_REVERSAL_NUMBER(84, IsoType.NUMERIC, 10),
    FEE_COLLECTIONS_NUMBER(85, IsoType.NUMERIC, 10),
    CREDITS_AMOUNT(86, IsoType.NUMERIC, 16),
    CREDITS_REVERSAL_AMOUNT(87, IsoType.NUMERIC, 16),
    DEBITS_AMOUNT(88, IsoType.NUMERIC, 16),
    DEBITS_REVERSAL_AMOUNT(89, IsoType.NUMERIC, 16),
    AUTHORISATIONS_REVERSAL_NUMBER(90, IsoType.NUMERIC, 10),
    COUNTRY_CODE_TRANSACTION_DESTINATION_INSTITUTION(91, IsoType.ALPHA, 3),
    COUNTRY_CODE_TRANSACTION_ORIGINATOR_INSTITUTION(92, IsoType.ALPHA, 3),
    TRANSACTION_DESTINATION_INSTITUTION_ID(93, IsoType.LLVAR, 11),
    TRANSACTION_ORIGINATOR_INSTITUTION_ID(94, IsoType.LLVAR, 11),
    CARD_ISSUER_REFERENCE_DATA(95, IsoType.LLVAR, 99),
    KEY_MANAGEMENT_DATA(96, IsoType.BINARY, 999),
    AMOUNT_NET_RECONCILIATION(97, IsoType.AMOUNT, 17),
    PAYEE(98, IsoType.ALPHA, 25),
    SETTLEMENT_INSTITUTION_IDENT_CODE(99, IsoType.LLVAR, 11),
    RECEIVING_INSTITUTION_IDENT_CODE(100, IsoType.LLVAR, 11),
    FILE_NAME(101, IsoType.LLVAR, 17),
    ACCOUNT_IDENTIFICATION_1(102, IsoType.LLVAR, 28),
    ACCOUNT_IDENTIFICATION_2(103, IsoType.LLVAR, 28),
    TRANSACTION_DESCRIPTION(104, IsoType.LLLVAR, 100),
    CREDITS_CHARGEBACK_AMOUNT(105, IsoType.LLLVAR, 16),
    DEBITS_CHARGEBACK_AMOUNT(106, IsoType.LLLVAR, 16),
    CREDITS_CHARGEBACK_NUMBER(107, IsoType.LLLVAR, 10),
    DEBITS_CHARGEBACK_NUMBER(108, IsoType.LLLVAR, 10),
    CREDITS_FEE_AMOUNTS(109, IsoType.LLLVAR, 84),
    DEBITS_FEE_AMOUNTS(110, IsoType.LLLVAR, 84),
    RESERVED_FOR_ISO_USE_2(111, IsoType.LLLVAR, 999),
    RESERVED_FOR_ISO_USE_3(112, IsoType.LLLVAR, 999),
    RESERVED_FOR_ISO_USE_4(113, IsoType.LLLVAR, 999),
    RESERVED_FOR_ISO_USE_5(114, IsoType.LLLVAR, 999),
    RESERVED_FOR_ISO_USE_6(115, IsoType.LLLVAR, 999),
    RESERVED_NATIONAL_USE(116, IsoType.LLLVAR, 999),
    RESERVED_NATIONAL_USE_2(116, IsoType.LLLVAR, 999),
    RESERVED_NATIONAL_USE_3(117, IsoType.LLLVAR, 999),
    RESERVED_NATIONAL_USE_4(118, IsoType.LLLVAR, 999),
    RESERVED_NATIONAL_USE_5(119, IsoType.LLLVAR, 999),
    RESERVED_PRIVATE_USE(120, IsoType.LLLVAR, 999),
    RESERVED_PRIVATE_USE_2(121, IsoType.LLLVAR, 999),
    ACQUIRER_ADDITIONAL_DATA_TRANSPORT_FIELD_LIMITED_USAGE(122, IsoType.LLLVAR, 999),
    PREVALIDATION_RESULTS(123, IsoType.LLLVAR, 999),
    RESERVED_PRIVATE_USE_3(124, IsoType.LLLVAR, 999),
    RESERVED_PRIVATE_USE_4(125, IsoType.LLLVAR, 999),
    ACQUIRER_REQUEST_ADDITIONAL_DATA(126, IsoType.LLLLVAR, 9999),
    RESERVED_PRIVATE_USE_5(127, IsoType.LLLVAR, 999),
    MAC_2(128, IsoType.BINARY, 8);

    private int id;

    private IsoType isoType;

    private int length;

    IsoField(int id, IsoType isoType, int length) {
        this.id = id;
        this.isoType = isoType;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public IsoType getIsoType() {
        return isoType;
    }

    public int getLength() {
        return length;
    }

    public static IsoField getById(int id) {
        for (IsoField isoField : values()) {
            if (isoField.getId() == id) {
                return isoField;
            }
        }
        return null;
    }
}
