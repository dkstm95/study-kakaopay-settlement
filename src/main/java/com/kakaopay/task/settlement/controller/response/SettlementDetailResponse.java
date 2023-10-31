package com.kakaopay.task.settlement.controller.response;

import com.kakaopay.task.settlement.domain.SettlementDetailStatus;

import java.time.Instant;

public record SettlementDetailResponse(
        Long settlementDetailId,
        Long amountToSettle,
        SettlementDetailStatus settlementDetailStatus,
        Instant settledAt,
        Long settlementRequestId,
        Long totalSettlementAmount,
        Instant requestedAt,
        String requestUserName,
        String requestUserProfileImageUrl) {
}
