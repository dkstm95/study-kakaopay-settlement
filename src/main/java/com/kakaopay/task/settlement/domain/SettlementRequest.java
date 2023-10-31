package com.kakaopay.task.settlement.domain;

import com.kakaopay.task.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Getter
@Entity
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettlementRequest extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long requestedUserId;

    private int totalReceiverCount;

    private long totalAmount;

    @Enumerated(EnumType.STRING)
    private SettlementRequestStatus status;

    private Instant requestedAt;

    private Instant settledAt;

    @OneToMany(mappedBy = "settlementRequest")
    private Set<SettlementDetail> settlementDetails = new HashSet<>();

    @Builder(builderMethodName = "testBuilder", builderClassName = "TestBuilder")
    private SettlementRequest(Long id, long requestedUserId, int totalReceiverCount, long totalAmount, SettlementRequestStatus status, Instant requestedAt, Instant settledAt, Set<SettlementDetail> settlementDetails) {
        this.id = id;
        this.requestedUserId = requestedUserId;
        this.totalReceiverCount = totalReceiverCount;
        this.totalAmount = totalAmount;
        this.status = status;
        this.requestedAt = requestedAt;
        this.settledAt = settledAt;
        this.settlementDetails = settlementDetails;
    }

    public static SettlementRequest create(long requestUserId, int totalReceiverCount, long totalAmount) {
        return SettlementRequest.builder()
                .requestedUserId(requestUserId)
                .totalReceiverCount(totalReceiverCount)
                .totalAmount(totalAmount)
                .status(SettlementRequestStatus.REQUESTED)
                .requestedAt(Instant.now())
                .settledAt(null)
                .build();
    }

    public void settleIfAllDetailsSettled() {
        if (areAllDetailsSettled()) {
            settle();
        }
    }

    private boolean areAllDetailsSettled() {
        return this.settlementDetails.stream().allMatch(SettlementDetail::isSettled);
    }

    private void settle() {
        this.status = SettlementRequestStatus.SETTLED;
        this.settledAt = Instant.now();
    }

    public Optional<SettlementDetail> findSettlementDetail(Long receivedUserId, Long settlementRequestId) {
        Objects.requireNonNull(receivedUserId);
        Objects.requireNonNull(settlementRequestId);

        return this.settlementDetails.stream()
                .filter(detail -> detail.isDetailForUserInRequest(receivedUserId, settlementRequestId))
                .findFirst();
    }

}
