package com.kakaopay.task.settlement.controller.validation;

import com.kakaopay.task.settlement.controller.request.SettlementCreationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AmountsMatchTotalValidator implements ConstraintValidator<AmountsMatchTotal, SettlementCreationRequest> {

    @Override
    public boolean isValid(SettlementCreationRequest value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        long totalAmount = value.getSettlementRequestInfoList().stream()
                .mapToLong(SettlementCreationRequest.SettlementRequestInfo::getAmount)
                .sum();

        return totalAmount == value.getTotalSettlementAmount();
    }

}
