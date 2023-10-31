package com.kakaopay.task.settlement.domain;

import org.junit.jupiter.api.Test;
import org.springframework.data.util.Streamable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SettlementRequestAggregateTest {

    @Test
    void filterByStatus_success() {
        // given
        SettlementRequestAggregate sut = SettlementRequestAggregate.of(
                Streamable.of(
                        SettlementRequest.testBuilder().id(1L).status(SettlementRequestStatus.REQUESTED).build(),
                        SettlementRequest.testBuilder().id(2L).status(SettlementRequestStatus.REQUESTED).build(),
                        SettlementRequest.testBuilder().id(3L).status(SettlementRequestStatus.SETTLED).build(),
                        SettlementRequest.testBuilder().id(4L).status(SettlementRequestStatus.REQUESTED).build(),
                        SettlementRequest.testBuilder().id(5L).status(SettlementRequestStatus.SETTLED).build())
        );

        // when
        List<SettlementRequest> filteredRequests = sut.filterByStatus(SettlementRequestStatus.REQUESTED);

        // then
        assertNotNull(filteredRequests);
        assertTrue(filteredRequests.stream().allMatch(request -> SettlementRequestStatus.REQUESTED == request.getStatus()));
    }

    @Test
    void filterByStatus_withNullStatus_returnsAllRequests() {
        // given
        SettlementRequestAggregate sut = SettlementRequestAggregate.of(
                Streamable.of(
                        SettlementRequest.testBuilder().id(1L).status(SettlementRequestStatus.REQUESTED).build(),
                        SettlementRequest.testBuilder().id(2L).status(SettlementRequestStatus.REQUESTED).build(),
                        SettlementRequest.testBuilder().id(3L).status(SettlementRequestStatus.SETTLED).build(),
                        SettlementRequest.testBuilder().id(4L).status(SettlementRequestStatus.REQUESTED).build(),
                        SettlementRequest.testBuilder().id(5L).status(SettlementRequestStatus.SETTLED).build())
        );

        // when
        List<SettlementRequest> filteredRequests = sut.filterByStatus(null);

        // then
        assertNotNull(filteredRequests);
        assertEquals(sut.getList().size(), filteredRequests.size());
    }

    @Test
    void getList_success() {
        // given
        SettlementRequestAggregate sut = SettlementRequestAggregate.of(
                Streamable.of(
                        SettlementRequest.testBuilder().id(1L).status(SettlementRequestStatus.REQUESTED).build(),
                        SettlementRequest.testBuilder().id(2L).status(SettlementRequestStatus.REQUESTED).build(),
                        SettlementRequest.testBuilder().id(3L).status(SettlementRequestStatus.SETTLED).build(),
                        SettlementRequest.testBuilder().id(4L).status(SettlementRequestStatus.REQUESTED).build(),
                        SettlementRequest.testBuilder().id(5L).status(SettlementRequestStatus.SETTLED).build())
        );

        // when
        List<SettlementRequest> allRequests = sut.getList();

        // then
        assertNotNull(allRequests);
        assertEquals(sut.stream().count(), allRequests.size());
    }

}