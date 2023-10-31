package com.kakaopay.task.settlement.controller;

import com.kakaopay.task.settlement.controller.request.SettlementCreationRequest;
import com.kakaopay.task.settlement.controller.response.ReceivedSettlementResponses;
import com.kakaopay.task.settlement.controller.response.RequestedSettlementResponses;
import com.kakaopay.task.settlement.controller.response.SettleResponse;
import com.kakaopay.task.settlement.controller.response.SettlementCreationResponse;
import com.kakaopay.task.settlement.domain.SettlementDetailStatus;
import com.kakaopay.task.settlement.domain.SettlementRequestStatus;
import com.kakaopay.task.settlement.service.SettlementCommandService;
import com.kakaopay.task.settlement.service.SettlementQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/settlements")
public class SettlementController {

    private final SettlementCommandService settlementCommandService;
    private final SettlementQueryService settlementQueryService;

    @GetMapping("/requests")
    public ResponseEntity<RequestedSettlementResponses> getSettlementRequests(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestParam(value = "status", required = false) SettlementRequestStatus status) {
        return ResponseEntity.ok(new RequestedSettlementResponses(settlementQueryService.getSettlementRequests(userId, status)));
    }

    @GetMapping("/received")
    public ResponseEntity<ReceivedSettlementResponses> getReceivedSettlements(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestParam(value = "status", required = false) SettlementDetailStatus status) {
        return ResponseEntity.ok(new ReceivedSettlementResponses(settlementQueryService.getReceivedSettlements(userId, status)));
    }

    @PostMapping("/requests")
    public ResponseEntity<SettlementCreationResponse> requestSettlement(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestBody @Valid SettlementCreationRequest request) {
        return ResponseEntity.ok(new SettlementCreationResponse(settlementCommandService.requestSettlement(userId, request.toRequestCommand(), request.toDetailsCommands())));
    }

    @PostMapping("/{settlementRequestId}/settle")
    public ResponseEntity<SettleResponse> settle(
            @RequestHeader("X-USER-ID") Long userId,
            @PathVariable("settlementRequestId") Long settlementRequestId) {
        settlementCommandService.settle(userId, settlementRequestId);
        return ResponseEntity.ok(new SettleResponse());
    }

}
