package com.kakaopay.task.settlement.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class SettlementDetailTest {

    @Test
    void settle_success() {
        // given
        SettlementDetail sut = SettlementDetail.testBuilder()
                .status(SettlementDetailStatus.REQUESTED)
                .settledAt(null)
                .build();

        // when
        sut.settle();

        // then
        assertEquals(SettlementDetailStatus.SETTLED, sut.getStatus());
        assertNotNull(sut.getSettledAt());
    }

    @Test
    void isDetailForUserInRequest_true() {
        // given
        SettlementDetail sut = SettlementDetail.testBuilder()
                .receivedUserId(1L)
                .request(SettlementRequest.testBuilder().id(2L).build())
                .build();

        // when
        boolean actual = sut.isDetailForUserInRequest(1L, 2L);

        // then
        assertTrue(actual);
    }

    @Test
    void isDetailForUserInRequest_false() {
        // given
        SettlementDetail sut = SettlementDetail.testBuilder()
                .receivedUserId(1L)
                .request(SettlementRequest.testBuilder().id(2L).build())
                .build();

        // when
        boolean actual = sut.isDetailForUserInRequest(1L, 3L);

        // then
        assertFalse(actual);
    }

}