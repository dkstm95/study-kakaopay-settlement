package com.kakaopay.task.settlement.controller.request;

import com.kakaopay.task.settlement.service.dto.SettlementDetailsCreationCommand;
import com.kakaopay.task.settlement.controller.validation.AmountsMatchTotal;
import com.kakaopay.task.settlement.service.dto.SettlementRequestCreationCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AmountsMatchTotal
public class SettlementCreationRequest {

    @NotNull @Positive Long totalSettlementAmount;

    @Size(min = 1) List<@NotNull SettlementRequestInfo> settlementRequestInfoList;

    @Getter
    public static class SettlementRequestInfo {
        @NotNull Long receivedUserId;
        @NotNull Long amount;
    }

    public SettlementRequestCreationCommand toRequestCommand() {
        return new SettlementRequestCreationCommand(this.settlementRequestInfoList.size(), this.totalSettlementAmount);
    }

    public SettlementDetailsCreationCommand toDetailsCommands() {
        return new SettlementDetailsCreationCommand(
                this.settlementRequestInfoList.stream()
                        .map(info -> new SettlementDetailsCreationCommand.Datum(info.receivedUserId, info.amount))
                        .toList());
    }

}
