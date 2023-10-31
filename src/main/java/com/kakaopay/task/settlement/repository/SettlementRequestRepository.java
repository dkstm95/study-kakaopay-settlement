package com.kakaopay.task.settlement.repository;

import com.kakaopay.task.settlement.domain.SettlementRequest;
import com.kakaopay.task.settlement.domain.SettlementRequestAggregate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SettlementRequestRepository extends JpaRepository<SettlementRequest, Long> {

    @EntityGraph(attributePaths = "settlementDetails")
    Optional<SettlementRequest> findSettlementRequestById(Long id);

    SettlementRequestAggregate findAllByRequestedUserIdOrderByCreatedAtDesc(Long requestUserId);

}
