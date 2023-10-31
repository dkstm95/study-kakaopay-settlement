package com.kakaopay.task.settlement.domain;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SettlementRequestTest {

    @Test
    void shouldChangeStatusToSettled_whenAllDetailsAreSettled() {
        // given
        Set<SettlementDetail> details = Set.of(
                SettlementDetail.testBuilder().status(SettlementDetailStatus.SETTLED).build(),
                SettlementDetail.testBuilder().status(SettlementDetailStatus.SETTLED).build()
        );
        SettlementRequest sut = SettlementRequest.testBuilder().settlementDetails(details).status(SettlementRequestStatus.REQUESTED).build();

        // when
        sut.settleIfAllDetailsSettled();

        // then
        assertEquals(SettlementRequestStatus.SETTLED, sut.getStatus());
    }

    @Test
    void shouldNotChangeStatusToSettled_whenAnyDetailIsNotSettled() {
        // given
        Set<SettlementDetail> details = Set.of(
                SettlementDetail.testBuilder().status(SettlementDetailStatus.SETTLED).build(),
                SettlementDetail.testBuilder().status(SettlementDetailStatus.REQUESTED).build()
        );
        SettlementRequest sut = SettlementRequest.testBuilder().settlementDetails(details).status(SettlementRequestStatus.REQUESTED).build();

        // when
        sut.settleIfAllDetailsSettled();

        // then
        assertEquals(SettlementRequestStatus.REQUESTED, sut.getStatus());
    }

}