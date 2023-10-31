package com.kakaopay.task.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class CommonResponse<T> {

    protected T data;
    protected String message;
    protected Integer code;

    protected CommonResponse<T> success() {
        this.message = "success";
        this.code = 1;
        return this;
    }

    protected CommonResponse<T> failed(int code, String message) {
        this.message = message;
        this.code = code;
        return this;
    }

    public void withData(T data) {
        this.data = data;
    }

    public void withNoData() {
        this.data = null;
    }

}
