package com.kakaopay.task.user.service.exception;

import lombok.Getter;

@Getter
public enum UserError {

    NOT_FOUND("사용자를 찾을 수 없습니다.")
    ;

    private final String message;

    UserError(String message) {
        this.message = message;
    }

}
