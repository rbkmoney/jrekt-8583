package com.rbkmoney.jrekt8583.model.posdata_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PINCaptureCapability {
    NO_PIN_CAPTURE_CAPABILITY("0"),
    UNKNOWN("1"),
    FOUR_CHARACTERS("4"),
    FIVE_CHARACTERS("5"),
    SIX_CHARACTERS("6"),
    SEVEN_CHARACTERS("7"),
    EIGHT_CHARACTERS("8"),
    NINE_CHARACTERS("9"),
    TEN_CHARACTERS("A"),
    ELEVEN_DIGITS("B"),
    TWELVE_DIGITS("C");

    private final String value;
}
