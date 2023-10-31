package com.kakaopay.task.settlement.controller.response;

import com.kakaopay.task.common.CommonResponse;

public class SettleResponse extends CommonResponse<Void> {

    public SettleResponse() {
        super();
        super.success().withNoData();
    }

}
