package com.kakaopay.task.settlement.service;

import com.kakaopay.task.common.configuration.ConfigurationService;
import com.kakaopay.task.notification.NotificationService;
import com.kakaopay.task.settlement.domain.SettlementDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SettlementSchedulerService {

    private static final int REMINDER_DEFAULT_PAGE_SIZE = 100;

    private final SettlementManager settlementManager;

    private final ConfigurationService configurationService;
    private final NotificationService notificationService;

    @Transactional
    public void sendReminders() {

        Instant reminderDate = getReminderDate();

        Pageable pageable = Pageable.ofSize(REMINDER_DEFAULT_PAGE_SIZE).withPage(0);

        Page<SettlementDetail> unsettledDetails;

        do {
            unsettledDetails = settlementManager.getUnsettledDetails(pageable, reminderDate);
            sendReminder(unsettledDetails.getContent());
            pageable = pageable.next();
        } while (unsettledDetails.hasNext());

    }

    private Instant getReminderDate() {
        int reminderIntervalDay = configurationService.getUnsettledReminderIntervalDay();
        Instant now = Instant.now();
        return now.minus(reminderIntervalDay, ChronoUnit.DAYS);
    }

    private void sendReminder(List<SettlementDetail> details) {
        Set<Long> receivedUserIds = details.stream().map(SettlementDetail::getReceivedUserId).collect(Collectors.toSet());

        notificationService.sendUnsettledReminders(receivedUserIds, details);

        details.forEach(SettlementDetail::markReminderSent);
    }

}
