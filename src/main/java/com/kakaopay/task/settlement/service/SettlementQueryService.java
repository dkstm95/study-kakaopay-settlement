package com.kakaopay.task.settlement.service;

import com.kakaopay.task.settlement.domain.*;
import com.kakaopay.task.settlement.service.dto.ReceivedSettlementsDto;
import com.kakaopay.task.settlement.service.dto.RequestedSettlementDto;
import com.kakaopay.task.user.domain.User;
import com.kakaopay.task.user.service.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class SettlementQueryService {

    private final SettlementManager settlementManager;
    private final UserManager userManager;

    public RequestedSettlementDto getSettlementRequests(Long requestUserId, SettlementRequestStatus status) {

        Objects.requireNonNull(requestUserId);

        SettlementRequestAggregate requests = settlementManager.getSettlementRequestsByRequestUserId(requestUserId);

        List<SettlementRequest> filteredRequests = requests.filterByStatus(status);

        return new RequestedSettlementDto(filteredRequests);

    }

    public ReceivedSettlementsDto getReceivedSettlements(Long receivedUserId, SettlementDetailStatus status) {

        Objects.requireNonNull(receivedUserId);

        SettlementDetailAggregate details = settlementManager.fetchSettlementDetailsByReceivedUserId(receivedUserId);

        List<SettlementDetail> filteredDetails = details.filterByStatus(status);

        Map<Long, User> userMap = getUserMap(filteredDetails);

        return new ReceivedSettlementsDto(filteredDetails, userMap);

    }

    private Map<Long, User> getUserMap(List<SettlementDetail> details) {
        Set<Long> requestUserIds = details.stream().map(detail -> detail.getSettlementRequest().getRequestedUserId()).collect(toSet());

        Map<Long, User> userMap = userManager.mapById(requestUserIds);

        Assert.isTrue(userMap.size() == requestUserIds.size(), "User not found");

        return userMap;
    }

}
