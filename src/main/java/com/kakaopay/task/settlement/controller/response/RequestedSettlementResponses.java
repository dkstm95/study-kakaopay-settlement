package com.kakaopay.task.settlement.controller.response;

import com.kakaopay.task.common.CommonResponse;
import com.kakaopay.task.settlement.domain.SettlementRequest;
import com.kakaopay.task.settlement.service.dto.RequestedSettlementDto;

import java.util.List;

public class RequestedSettlementResponses extends CommonResponse<RequestedSettlementResponses.Data> {

    public RequestedSettlementResponses(RequestedSettlementDto dto) {
        super();
        super.success().withData(Data.of(dto.settlementRequests()));
    }

    public record Data(List<RequestedSettlementResponse> list) {
        static Data of(List<SettlementRequest> settlementRequests) {
            List<RequestedSettlementResponse> responses = settlementRequests.stream()
                    .map(settlementRequest -> new RequestedSettlementResponse(
                            settlementRequest.getId(),
                            settlementRequest.getTotalAmount(),
                            settlementRequest.getStatus(),
                            settlementRequest.getRequestedAt(),
                            settlementRequest.getSettledAt(),
                            settlementRequest.getTotalReceiverCount()))
                    .toList();
            return new Data(responses);
        }
    }

}
