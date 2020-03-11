package com.rbkmoney.jrekt8583.model.posdata_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CardDataInputMode {
    UNSPECIFIED("0"),
    MANUAL_NO_TERMINAL("1"),
    MAGNETIC_STRIPE_READ2("2"),
    BAR_CODE_READ("3"),
    OPTICAL_CHARACTER_RECOGNITION_CODING_READ("4"),
    SMART_CARD_READ("5"),
    KEY_ENTERED("6"),
    MAGNETIC_STRIPE_READ_IN_FALLBACK_TO_SMART_CARD_READ("8"),
    FULL_MAGNETIC_STRIPE_READ("9"),
    CONTACTLESS_MAGNETIC_STRIPE_READ("A"),
    CREDENTIAL_ON_FILE_MERCHANT_INITIATED_AUTHORIZATION_RECURRING("J"),
    CONTACTLESS_SMART_CARD_READ("M"),
    PAYPASS_MAPPING_SERVICE("N"),
    PAN_ENTRY_VIA_ELECTRONIC_COMMERCE_INCLUDING_REMOTE_CHIP("R"),
    ELECTRONIC_COMMERCE_SET_WITHOUT_CARDHOLDER_CERTIFICATE("S"),
    ELECTRONIC_COMMERCE_SET_WITH_CARDHOLDER_CERTIFICATE("T"),
    ELECTRONIC_COMMERCE_NO_SECURITY("U"),
    ELECTRONIC_COMMERCE_CHANNEL_ENCRYPTION("V"),
    CREDENTIAL_ON_FILE_CONSUMER_INITIATED_AUTHORIZATION("W");

    private final String value;

    public static CardDataInputMode fromValue(String val){
        return Arrays.stream(CardDataInputMode.values())
                .filter(c -> c.getValue().equals(val))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Unknown value " + val));
    }
}
