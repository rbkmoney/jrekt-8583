package com.rbkmoney.jrekt8583.model.posdata_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TerminalOutputCapability {
    UNKNOWN("0"),
    NONE("1"),
    PRINTING("2"),
    DISPLAY("3"),
    PRINTING_AND_DISPLAY("4");

    private final String code;
}
