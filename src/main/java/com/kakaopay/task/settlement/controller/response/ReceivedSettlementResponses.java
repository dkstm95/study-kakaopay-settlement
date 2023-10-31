package com.kakaopay.task.settlement.controller.response;

import com.kakaopay.task.common.CommonResponse;
import com.kakaopay.task.settlement.domain.SettlementDetail;
import com.kakaopay.task.settlement.domain.SettlementRequest;
import com.kakaopay.task.settlement.service.dto.ReceivedSettlementsDto;
import com.kakaopay.task.user.domain.User;

import java.util.List;
import java.util.Map;

public class ReceivedSettlementResponses extends CommonResponse<ReceivedSettlementResponses.Data> {

    public ReceivedSettlementResponses(ReceivedSettlementsDto dto) {
        super();
        super.success().withData(Data.of(dto.settlementDetails(), dto.requestUserMap()));
    }

    public record Data(List<SettlementDetailResponse> list) {
        static Data of(List<SettlementDetail> settlementDetails, Map<Long, User> requestUserMap) {
            List<SettlementDetailResponse> responses = settlementDetails.stream()
                    .map(settlementDetail -> {
                        SettlementRequest settlementRequest = settlementDetail.getSettlementRequest();
                        User requestUser = requestUserMap.get(settlementRequest.getRequestedUserId());
                        return new SettlementDetailResponse(
                                settlementDetail.getId(),
                                settlementDetail.getAmount(),
                                settlementDetail.getStatus(),
                                settlementDetail.getSettledAt(),
                                settlementRequest.getId(),
                                settlementRequest.getTotalAmount(),
                                settlementRequest.getRequestedAt(),
                                requestUser.getNickname(),
                                requestUser.getProfileImageUrl());
                    })
                    .toList();
            return new Data(responses);
        }
    }

}
