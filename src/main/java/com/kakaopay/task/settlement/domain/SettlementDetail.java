package com.kakaopay.task.settlement.domain;

import com.kakaopay.task.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettlementDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "settlement_request_id")
    private SettlementRequest settlementRequest;

    private long receivedUserId;

    private long amount;

    @Enumerated(EnumType.STRING)
    private SettlementDetailStatus status;

    private Instant settledAt;

    private Instant lastRemindAt;

    @Builder(builderMethodName = "testBuilder", builderClassName = "TestBuilder")
    private SettlementDetail(Long id, SettlementRequest request, long receivedUserId, long amount, SettlementDetailStatus status, Instant settledAt, Instant lastRemindAt) {
        this.id = id;
        this.settlementRequest = request;
        this.receivedUserId = receivedUserId;
        this.amount = amount;
        this.status = status;
        this.settledAt = settledAt;
        this.lastRemindAt = lastRemindAt;
    }

    public static SettlementDetail create(SettlementRequest request, long receivedUserId, long amount) {
        return SettlementDetail.builder()
                .settlementRequest(request)
                .receivedUserId(receivedUserId)
                .amount(amount)
                .status(SettlementDetailStatus.REQUESTED)
                .settledAt(null)
                .lastRemindAt(null)
                .build();
    }

    public void settle() {
        this.status = SettlementDetailStatus.SETTLED;
        this.settledAt = Instant.now();
    }

    public boolean isSettled() {
        return SettlementDetailStatus.SETTLED == this.status;
    }

    public boolean isDetailForUserInRequest(long userId, long requestId) {
        return userId == this.receivedUserId && requestId == this.settlementRequest.getId();
    }


    public void markReminderSent() {
        this.lastRemindAt = Instant.now();
    }

}
