package com.kakaopay.task.transfer;

import com.kakaopay.task.settlement.domain.SettlementDetail;
import com.kakaopay.task.settlement.domain.SettlementRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MockMoneyTransferService implements MoneyTransferService {

    @Override
    @Transactional
    public void transferAndSettle(long fromUserId, SettlementRequest request, SettlementDetail detail) {

        System.out.println("Money transferred from " + fromUserId + " to " + request.getRequestedUserId() + " with amount " + detail.getAmount());

        detail.settle();

    }

}
