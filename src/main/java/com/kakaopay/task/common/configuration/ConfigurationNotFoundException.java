package com.kakaopay.task.common.configuration;

import lombok.Getter;

@Getter
public class ConfigurationNotFoundException extends RuntimeException {

    public ConfigurationNotFoundException(String key) {
        super("Configuration not found: " + key);
    }

}
