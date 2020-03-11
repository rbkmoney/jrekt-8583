package com.rbkmoney.jrekt8583.model.posdata_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardDataInputCapability {
    UNKNOWN("0"),
    MANUAL_NO_TERMINAL("1"),
    MAGNETIC_STRIPE("2"),
    BAR_CODE_READ("3"),
    OPTICAL_CHARACTER_RECOGNITION_CODING_READER("4"),
    SMART_CARD_READ("5"),
    KEY_ENTRY_ONLY("6"),
    CONTACTLESS_MAGNETIC_STRIPE_READ("A"),
    CONTACTLESS_SMART_CARD_READ("M"),
    CONTACTLESS_ONLY_TERMINAL("W");

    private final String value;
}
