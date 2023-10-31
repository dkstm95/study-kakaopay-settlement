package com.kakaopay.task.settlement.service.exception;

import lombok.Getter;

@Getter
public class SettlementDetailException extends RuntimeException {

    private final SettlementError error;
    private final long settlementRequestId;
    private final Long receivedUserId;

    public SettlementDetailException(SettlementError settlementError, long settlementRequestId) {
        super(makeMessage(settlementError, settlementRequestId, null));
        this.error = settlementError;
        this.settlementRequestId = settlementRequestId;
        this.receivedUserId = null;
    }

    private static String makeMessage(SettlementError settlementError, long settlementRequestId, Long receivedUserId) {
        return settlementError.getMessage() + " (receivedUserId: " + receivedUserId + ", receivedSettlementRequestId: " + settlementRequestId + ")";
    }

}
