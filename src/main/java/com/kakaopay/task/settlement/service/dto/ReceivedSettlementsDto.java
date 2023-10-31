package com.kakaopay.task.settlement.service.dto;

import com.kakaopay.task.settlement.domain.SettlementDetail;
import com.kakaopay.task.user.domain.User;

import java.util.List;
import java.util.Map;

public record ReceivedSettlementsDto(List<SettlementDetail> settlementDetails, Map<Long, User> requestUserMap) {
}
