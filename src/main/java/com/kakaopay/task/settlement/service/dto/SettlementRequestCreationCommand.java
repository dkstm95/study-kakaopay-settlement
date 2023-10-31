package com.kakaopay.task.settlement.service.dto;

import com.kakaopay.task.settlement.domain.SettlementRequest;

public record SettlementRequestCreationCommand(int totalReceiverCount, long totalSettlementAmount) {

    public SettlementRequest toSettlementRequest(long requestUserId) {
        return SettlementRequest.create(requestUserId, totalReceiverCount, totalSettlementAmount);
    }

}
