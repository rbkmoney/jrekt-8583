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

    @Override
    public String toString() {
        return cardDataInputCapability.getValue() +
                cardholderAuthenticationCapability.getValue() +
                cardCaptureCapability.getValue() +
                operatingEnvironment.getValue() +
                cardholderPresent.getValue() +
                cardPresent.getValue() +
                cardDataInputMode.getValue() +
                cardholderAuthenticationMethod.getValue() +
                cardholderAuthenticationEntity.getValue() +
                cardDataOutputCapability.getValue() +
                terminalOutputCapability.getValue() +
                pinCaptureCapability.getValue();
    }
}
