CREATE TABLE user
(
    user_id   VARCHAR(36),
    user_name VARCHAR(100) NOT NULL UNIQUE,
    password  VARCHAR(100) NOT NULL,
    PRIMARY KEY (user_id)
) ENGINE = InnoDB CHARSET = utf8mb4;

INSERT INTO user(user_id, user_name, password) VALUES ("1", "testuser", "password123");