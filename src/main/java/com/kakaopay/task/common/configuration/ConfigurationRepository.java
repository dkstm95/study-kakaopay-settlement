package com.kakaopay.task.common.configuration;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConfigurationRepository extends CrudRepository<Configuration, Long> {
    Optional<Configuration> findByConfigKey(String key);
}
