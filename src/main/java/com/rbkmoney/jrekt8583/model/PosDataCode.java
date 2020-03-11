package com.rbkmoney.jrekt8583.model;

import com.rbkmoney.jrekt8583.model.posdata_code.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PosDataCode {
    private CardDataInputCapability cardDataInputCapability;
    private CardholderAuthenticationCapability cardholderAuthenticationCapability;
    private CardCaptureCapability cardCaptureCapability;
    private OperatingEnvironment operatingEnvironment;
    private CardholderPresent cardholderPresent;
    private CardPresent cardPresent;
    private CardDataInputMode cardDataInputMode;
    private CardholderAuthenticationMethod cardholderAuthenticationMethod;
    private CardholderAuthenticationEntity cardholderAuthenticationEntity;
    private CardDataOutputCapability cardDataOutputCapability;
    private TerminalOutputCapability terminalOutputCapability;
    private PINCaptureCapability pinCaptureCapability;

    public String toIsoString() {
        return cardDataInputCapability.getCode() +
                cardholderAuthenticationCapability.getCode() +
                cardCaptureCapability.getCode() +
                operatingEnvironment.getCode() +
                cardholderPresent.getCode() +
                cardPresent.getCode() +
                cardDataInputMode.getCode() +
                cardholderAuthenticationMethod.getCode() +
                cardholderAuthenticationEntity.getCode() +
                cardDataOutputCapability.getCode() +
                terminalOutputCapability.getCode() +
                pinCaptureCapability.getCode();
    }
}
