package com.kakaopay.task.transfer;

import com.kakaopay.task.settlement.domain.SettlementDetail;
import com.kakaopay.task.settlement.domain.SettlementRequest;

public interface MoneyTransferService {

    void transferAndSettle(long fromUserId, SettlementRequest request, SettlementDetail detail);

}
