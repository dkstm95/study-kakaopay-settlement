package com.kakaopay.task.settlement.service;

import com.kakaopay.task.settlement.domain.SettlementDetail;
import com.kakaopay.task.settlement.domain.SettlementRequest;
import com.kakaopay.task.settlement.service.exception.SettlementDetailException;
import com.kakaopay.task.settlement.service.exception.SettlementError;
import com.kakaopay.task.settlement.service.dto.SettlementDetailsCreationCommand;
import com.kakaopay.task.settlement.service.dto.SettlementRequestCreationCommand;
import com.kakaopay.task.settlement.service.event.SettleCompletedEvent;
import com.kakaopay.task.transfer.MockMoneyTransferService;
import com.kakaopay.task.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class SettlementCommandService {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final SettlementManager settlementManager;

    private final MockMoneyTransferService moneyTransferService;
    private final UserService userService;

    @Transactional
    public long requestSettlement(Long requestUserId,
                                  SettlementRequestCreationCommand requestCreationCommand,
                                  SettlementDetailsCreationCommand detailsCreationCommand) {

        validateUserIds(Objects.requireNonNull(requestUserId), detailsCreationCommand);

        SettlementRequest newRequest = requestCreationCommand.toSettlementRequest(requestUserId);
        SettlementRequest savedRequest = settlementManager.saveSettlementRequest(newRequest);

        List<SettlementDetail> newDetails = detailsCreationCommand.toSettlementDetails(savedRequest);
        settlementManager.saveSettlementDetails(newDetails);

        return savedRequest.getId();

    }

    private void validateUserIds(Long requestUserId, SettlementDetailsCreationCommand detailsCreationCommand) {
        Set<Long> receivedUserIds = detailsCreationCommand.data().stream()
                .map(SettlementDetailsCreationCommand.Datum::receivedUserId)
                .collect(toSet());

        receivedUserIds.add(requestUserId);

        userService.validateAllUserIdExists(receivedUserIds);
    }

    @Transactional
    public void settle(Long receivedUserId, Long settlementRequestId) {

        SettlementRequest request = settlementManager.fetchSettlementRequestById(settlementRequestId);

        SettlementDetail detail = request.findSettlementDetail(receivedUserId, settlementRequestId)
                .orElseThrow(() -> new SettlementDetailException(SettlementError.DETAIL_NOT_FOUND, receivedUserId));

        moneyTransferService.transferAndSettle(receivedUserId, request, detail);

        applicationEventPublisher.publishEvent(new SettleCompletedEvent(settlementRequestId));

    }

    @Transactional
    @EventListener
    public void handleSettleCompletedEvent(SettleCompletedEvent event) {

        long settlementRequestId = Objects.requireNonNull(event.settlementRequestId());

        SettlementRequest request = settlementManager.fetchSettlementRequestById(settlementRequestId);

        request.settleIfAllDetailsSettled();

    }

}
