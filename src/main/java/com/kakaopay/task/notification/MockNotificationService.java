package com.kakaopay.task.notification;

import com.kakaopay.task.settlement.domain.SettlementDetail;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MockNotificationService implements NotificationService {

    @Override
    @Async
    public void sendUnsettledReminders(Collection<Long> userIds, List<SettlementDetail> unsettledDetails) {

        Map<Long, List<SettlementDetail>> unsettledMap = unsettledDetails.stream()
                .collect(Collectors.groupingBy(SettlementDetail::getReceivedUserId));

        for (Long userId : userIds) {
            List<SettlementDetail> details = unsettledMap.get(userId);
            System.out.println("sendUnsettledReminders: userId=" + userId + ", details=" + details);
        }

    }

}
