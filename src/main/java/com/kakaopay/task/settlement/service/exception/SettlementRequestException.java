package com.kakaopay.task.settlement.service.exception;

import lombok.Getter;

@Getter
public class SettlementRequestException extends RuntimeException {

    private final SettlementError error;
    private final long settlementRequestId;

    public SettlementRequestException(SettlementError settlementError, long settlementRequestId) {
        super(makeMessage(settlementError, settlementRequestId));
        this.error = settlementError;
        this.settlementRequestId = settlementRequestId;
    }

    private static String makeMessage(SettlementError settlementError, long settlementRequestId) {
        return settlementError.getMessage() + " (receivedSettlementRequestId: " + settlementRequestId + ")";
    }

}
