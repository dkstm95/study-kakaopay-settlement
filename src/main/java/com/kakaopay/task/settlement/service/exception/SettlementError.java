package com.kakaopay.task.settlement.service.exception;

import lombok.Getter;

@Getter
public enum SettlementError {

    REQUEST_NOT_FOUND("Settlement request not found."),
    DETAIL_NOT_FOUND("Settlement detail not found.")
    ;

    private final String message;

    SettlementError(String message) {
        this.message = message;
    }

}
