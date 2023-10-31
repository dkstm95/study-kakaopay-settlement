INSERT INTO settlement_request (
    requested_user_id,
    total_receiver_count,
    total_amount,
    status,
    requested_at,
    settled_at,
    created_at,
    updated_at
) VALUES (
     1,
     3,
     10000,
     'REQUESTED',
     '2023-10-29 10:00:00',
     null,
     '2023-10-29 09:00:00',
     null
 );

INSERT INTO settlement_detail (
    settlement_request_id,
    received_user_id,
    amount,
    status,
    settled_at,
    last_remind_at,
    created_at,
    updated_at
) VALUES (
     1,
     2,
     2000,
     'REQUESTED',
     null,
     null,
     '2023-10-29 11:00:00',
     '2023-10-29 11:00:00'
), (
    1,
    3,
    3000,
    'REQUESTED',
    null,
    null,
    '2023-10-29 11:00:00',
    '2023-10-29 11:00:00'
), (
    1,
    4,
    5000,
    'REQUESTED',
    null,
    null,
    '2023-10-29 11:00:00',
    '2023-10-29 11:00:00'
 );

INSERT INTO "user" (
    nickname,
    profile_image_url,
    created_at,
    updated_at
) VALUES (
     '테스트닉네임1',
     'http://example.com/profile.jpg',
     '2023-10-29 11:00:00',
     '2023-10-29 11:00:00'
), (
    '테스트닉네임2',
    'http://example.com/profile.jpg',
    '2023-10-29 11:00:00',
    '2023-10-29 11:00:00'
), (
    '테스트닉네임3',
    'http://example.com/profile.jpg',
    '2023-10-29 11:00:00',
    '2023-10-29 11:00:00'
), (
    '테스트닉네임4',
    'http://example.com/profile.jpg',
    '2023-10-29 11:00:00',
    '2023-10-29 11:00:00'
);


INSERT INTO room (
    created_at,
    updated_at
) VALUES (
     '2023-10-29 11:00:00',
     '2023-10-29 11:00:00'
);

INSERT INTO configuration (
    config_key,
    config_value,
    created_at,
    updated_at
) VALUES (
     'unsettled-reminder-interval-day',
     '1',
     '2023-10-29 11:00:00',
     '2023-10-29 11:00:00'
);