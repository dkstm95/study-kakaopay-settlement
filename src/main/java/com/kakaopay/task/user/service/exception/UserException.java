package com.kakaopay.task.user.service.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private final UserError error;
    private final long userId;

    public UserException(UserError userError, long userId) {
        super(makeMessage(userError, userId));
        this.error = userError;
        this.userId = userId;
    }

    private static String makeMessage(UserError userError, long userId) {
        return userError.getMessage() + " (receivedUserId: " + userId + ")";
    }

}
