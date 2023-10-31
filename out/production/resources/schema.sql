CREATE TABLE IF NOT EXISTS settlement_request (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP default CURRENT_TIMESTAMP not null,
    updated_at TIMESTAMP,
    requested_user_id BIGINT not null,
    total_receiver_count INT not null,
    total_amount BIGINT not null,
    status VARCHAR(10) not null,
    requested_at TIMESTAMP not null,
    settled_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS settlement_detail (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
     updated_at TIMESTAMP,
     settlement_request_id BIGINT NOT NULL,
     received_user_id BIGINT NOT NULL,
     amount BIGINT NOT NULL,
     status VARCHAR(255) NOT NULL,
     settled_at TIMESTAMP,
     last_remind_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS "user" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    nickname VARCHAR(255) NOT NULL,
    profile_image_url VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS room (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS configuration (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    config_key VARCHAR(255) NOT NULL,
    config_value VARCHAR(255) NOT NULL
);