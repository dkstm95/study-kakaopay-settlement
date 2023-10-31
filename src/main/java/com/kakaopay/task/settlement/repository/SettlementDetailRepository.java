package com.kakaopay.task.settlement.repository;

import com.kakaopay.task.settlement.domain.SettlementDetail;
import com.kakaopay.task.settlement.domain.SettlementDetailAggregate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface SettlementDetailRepository extends JpaRepository<SettlementDetail, Long> {

    @EntityGraph(attributePaths = "settlementRequest")
    SettlementDetailAggregate findAllByReceivedUserIdOrderByCreatedAtDesc(Long receiveUserId);

    @Query(value = "SELECT sd FROM SettlementDetail sd " +
            "JOIN FETCH sd.settlementRequest sr " +
            "WHERE sd.status = 'REQUESTED' " +
            "AND (sd.lastRemindAt IS NULL OR sd.lastRemindAt < :date)",
            countQuery = "SELECT COUNT(sd) FROM SettlementDetail sd " +
            "WHERE sd.status = 'REQUESTED' " +
            "AND (sd.lastRemindAt IS NULL OR sd.lastRemindAt < :date)")
    Page<SettlementDetail> getUnsettledDetails(@Param("date") Instant date, Pageable pageable);

}
