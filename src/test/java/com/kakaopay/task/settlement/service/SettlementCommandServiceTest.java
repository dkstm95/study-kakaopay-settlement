package com.kakaopay.task.settlement.service;

import com.kakaopay.task.settlement.domain.SettlementDetail;
import com.kakaopay.task.settlement.domain.SettlementDetailStatus;
import com.kakaopay.task.settlement.domain.SettlementRequest;
import com.kakaopay.task.settlement.domain.SettlementRequestStatus;
import com.kakaopay.task.settlement.service.dto.SettlementDetailsCreationCommand;
import com.kakaopay.task.settlement.service.dto.SettlementRequestCreationCommand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SettlementCommandServiceTest {

    @Test
    void shouldCreateSettlementRequest() {
        // given
        SettlementRequestCreationCommand sut = new SettlementRequestCreationCommand(2, 3000L);

        // when
        SettlementRequest result = sut.toSettlementRequest(1L);

        // then
        assertEquals(2, result.getTotalReceiverCount());
        assertEquals(3000L, result.getTotalAmount());
        assertEquals(SettlementRequestStatus.REQUESTED, result.getStatus());
        assertNull(result.getSettledAt());
        assertNotNull(result.getRequestedAt());
    }

    @Test
    void shouldCreateSettlementDetail() {
        // given
        SettlementRequest request = SettlementRequest.testBuilder().id(1L).build();
        SettlementDetailsCreationCommand sut = new SettlementDetailsCreationCommand(
                List.of(
                        new SettlementDetailsCreationCommand.Datum(2L, 1000L),
                        new SettlementDetailsCreationCommand.Datum(3L, 2000L)
                ));

        // when
        List<SettlementDetail> result = sut.toSettlementDetails(request);

        // then
        assertEquals(2, result.size());

        assertEquals(1L, result.get(0).getSettlementRequest().getId());
        assertEquals(2L, result.get(0).getReceivedUserId());
        assertEquals(1000L, result.get(0).getAmount());
        assertEquals(SettlementDetailStatus.REQUESTED, result.get(0).getStatus());
        assertNull(result.get(0).getSettledAt());
        assertNull(result.get(0).getLastRemindAt());

        assertEquals(1L, result.get(1).getSettlementRequest().getId());
        assertEquals(3L, result.get(1).getReceivedUserId());
        assertEquals(2000L, result.get(1).getAmount());
        assertEquals(SettlementDetailStatus.REQUESTED, result.get(1).getStatus());
        assertNull(result.get(1).getSettledAt());
        assertNull(result.get(1).getLastRemindAt());
    }

}