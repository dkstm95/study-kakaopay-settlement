package com.kakaopay.task.settlement.controller.response;

import com.kakaopay.task.common.CommonResponse;

public class SettlementCreationResponse extends CommonResponse<SettlementCreationResponse.Data> {

    public SettlementCreationResponse(long settlementRequestId) {
        super();
        super.success().withData(new Data(settlementRequestId));
    }

    public record Data(long settlementRequestId) {
    }

}
