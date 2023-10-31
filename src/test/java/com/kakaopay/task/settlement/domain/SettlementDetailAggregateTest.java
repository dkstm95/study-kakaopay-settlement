package com.kakaopay.task.settlement.domain;

import org.junit.jupiter.api.Test;
import org.springframework.data.util.Streamable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SettlementDetailAggregateTest {

    @Test
    void shouldFilterByStatus_success() {
        // given
        SettlementDetailAggregate sut = SettlementDetailAggregate.of(
                Streamable.of(
                        SettlementDetail.testBuilder().id(1L).status(SettlementDetailStatus.REQUESTED).build(),
                        SettlementDetail.testBuilder().id(2L).status(SettlementDetailStatus.REQUESTED).build(),
                        SettlementDetail.testBuilder().id(3L).status(SettlementDetailStatus.SETTLED).build(),
                        SettlementDetail.testBuilder().id(4L).status(SettlementDetailStatus.REQUESTED).build(),
                        SettlementDetail.testBuilder().id(5L).status(SettlementDetailStatus.SETTLED).build())
        );

        // when
        List<SettlementDetail> filteredDetails = sut.filterByStatus(SettlementDetailStatus.REQUESTED);

        // then
        assertNotNull(filteredDetails);
        assertTrue(filteredDetails.stream().allMatch(detail -> SettlementDetailStatus.REQUESTED == detail.getStatus()));
    }

    @Test
    void shouldFilterByStatus_withNullStatus_returnsAllDetails() {
        // given
        SettlementDetailAggregate sut = SettlementDetailAggregate.of(
                Streamable.of(
                        SettlementDetail.testBuilder().id(1L).status(SettlementDetailStatus.REQUESTED).build(),
                        SettlementDetail.testBuilder().id(2L).status(SettlementDetailStatus.REQUESTED).build(),
                        SettlementDetail.testBuilder().id(3L).status(SettlementDetailStatus.SETTLED).build(),
                        SettlementDetail.testBuilder().id(4L).status(SettlementDetailStatus.REQUESTED).build(),
                        SettlementDetail.testBuilder().id(5L).status(SettlementDetailStatus.SETTLED).build())
        );

        // when
        List<SettlementDetail> filteredDetails = sut.filterByStatus(null);

        // then
        assertNotNull(filteredDetails);
        assertEquals(sut.getList().size(), filteredDetails.size());
    }

    @Test
    void getList_success() {
        // given
        SettlementDetailAggregate sut = SettlementDetailAggregate.of(
                Streamable.of(
                        SettlementDetail.testBuilder().id(1L).status(SettlementDetailStatus.REQUESTED).build(),
                        SettlementDetail.testBuilder().id(2L).status(SettlementDetailStatus.REQUESTED).build(),
                        SettlementDetail.testBuilder().id(3L).status(SettlementDetailStatus.SETTLED).build(),
                        SettlementDetail.testBuilder().id(4L).status(SettlementDetailStatus.REQUESTED).build(),
                        SettlementDetail.testBuilder().id(5L).status(SettlementDetailStatus.SETTLED).build())
        );

        // when
        List<SettlementDetail> allDetails = sut.getList();

        // then
        assertNotNull(allDetails);
        assertEquals(sut.stream().count(), allDetails.size());
    }

}