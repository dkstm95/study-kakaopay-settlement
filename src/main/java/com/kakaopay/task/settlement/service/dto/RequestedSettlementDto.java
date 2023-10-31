package com.kakaopay.task.settlement.service.dto;

import com.kakaopay.task.settlement.domain.SettlementRequest;

import java.util.List;

public record RequestedSettlementDto(List<SettlementRequest> settlementRequests) {
}
