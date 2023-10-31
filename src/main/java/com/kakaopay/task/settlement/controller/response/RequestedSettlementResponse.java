package com.kakaopay.task.settlement.controller.response;

import com.kakaopay.task.settlement.domain.SettlementRequestStatus;

import java.time.Instant;

public record RequestedSettlementResponse(
        Long settlementRequestId,
        Long totalAmount,
        SettlementRequestStatus settlementRequestStatus,
        Instant requestedAt,
        Instant settledAt,
        int totalReceiverCount) {
}
