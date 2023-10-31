package com.kakaopay.task.settlement.service;

import com.kakaopay.task.settlement.domain.SettlementDetail;
import com.kakaopay.task.settlement.domain.SettlementDetailAggregate;
import com.kakaopay.task.settlement.domain.SettlementRequest;
import com.kakaopay.task.settlement.domain.SettlementRequestAggregate;
import com.kakaopay.task.settlement.service.exception.SettlementError;
import com.kakaopay.task.settlement.service.exception.SettlementRequestException;
import com.kakaopay.task.settlement.repository.SettlementDetailRepository;
import com.kakaopay.task.settlement.repository.SettlementRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collection;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SettlementManager {

    private final SettlementRequestRepository settlementRequestRepository;
    private final SettlementDetailRepository settlementDetailRepository;

    public SettlementRequest fetchSettlementRequestById(Long id) {
        return settlementRequestRepository.findSettlementRequestById(Objects.requireNonNull(id))
                .orElseThrow(() -> new SettlementRequestException(SettlementError.REQUEST_NOT_FOUND, id));
    }

    public SettlementRequestAggregate getSettlementRequestsByRequestUserId(Long requestUserId) {
        return settlementRequestRepository.findAllByRequestedUserIdOrderByCreatedAtDesc(requestUserId);
    }

    @Transactional
    public SettlementRequest saveSettlementRequest(SettlementRequest request) {
        return settlementRequestRepository.save(request);
    }

    public SettlementDetailAggregate fetchSettlementDetailsByReceivedUserId(Long receivedUserId) {
        return settlementDetailRepository.findAllByReceivedUserIdOrderByCreatedAtDesc(receivedUserId);
    }

    public Page<SettlementDetail> getUnsettledDetails(Pageable pageable, Instant reminderDate) {
        return settlementDetailRepository.getUnsettledDetails(reminderDate, pageable);
    }

    @Transactional
    public void saveSettlementDetails(Collection<SettlementDetail> newDetails) {
        settlementDetailRepository.saveAll(newDetails);
    }

}
