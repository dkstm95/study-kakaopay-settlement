package com.kakaopay.task.scheduler;

import com.kakaopay.task.settlement.service.SettlementSchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SettlementScheduler {

    private final SettlementSchedulerService settlementSchedulerService;

    @Scheduled(cron = "0 0 0 * * *")
    public void sendReminders() {
        settlementSchedulerService.sendReminders();
    }

}
