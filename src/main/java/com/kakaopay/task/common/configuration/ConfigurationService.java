package com.kakaopay.task.common.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfigurationService {

    private static final String UNSETTLED_REMINDER_INTERVAL_DAY = "unsettled-reminder-interval-day";

    private final ConfigurationRepository configurationRepository;

    private Optional<Configuration> findByKey(String key) {
        return configurationRepository.findByConfigKey(key);
    }

    private Configuration getByKey(String key) {
        return findByKey(key).orElseThrow(() -> new ConfigurationNotFoundException(key));
    }

    public int getUnsettledReminderIntervalDay() {
        return Integer.parseInt(getByKey(UNSETTLED_REMINDER_INTERVAL_DAY).getConfigValue());
    }

}
