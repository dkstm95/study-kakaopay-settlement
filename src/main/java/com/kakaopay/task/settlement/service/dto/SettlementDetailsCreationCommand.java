package com.kakaopay.task.settlement.service.dto;

import com.kakaopay.task.settlement.domain.SettlementDetail;
import com.kakaopay.task.settlement.domain.SettlementRequest;

import java.util.List;

public record SettlementDetailsCreationCommand(List<Datum> data) {

    public List<SettlementDetail> toSettlementDetails(SettlementRequest savedRequest) {
        return this.data.stream()
                .map(datum -> SettlementDetail.create(savedRequest, datum.receivedUserId(), datum.amountToSettle()))
                .toList();
    }

    public record Datum(long receivedUserId, long amountToSettle) {
    }

}
