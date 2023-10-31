package com.kakaopay.task.notification;

import com.kakaopay.task.settlement.domain.SettlementDetail;

import java.util.Collection;
import java.util.List;

public interface NotificationService {

    void sendUnsettledReminders(Collection<Long> userIds, List<SettlementDetail> unsettledDetails);

}
