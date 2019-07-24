SET GLOBAL TIME_ZONE = '+04:00';
CREATE DATABASE trello_demo;

USE trello_demo;
CREATE TABLE users
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    email           VARCHAR(255) NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,
    full_name       VARCHAR(255) NOT NULL,
    user_name       VARCHAR(255) NOT NULL,
    initial         CHAR(3),
    image_url       VARCHAR(255) DEFAULT 'images/user.png',
    about_me        VARCHAR(255),
    activation_code varchar(255)
);

CREATE TABLE roles
(
    id   TINYINT PRIMARY KEY AUTO_INCREMENT,
    role CHAR(70) NOT NULL UNIQUE DEFAULT 'USER'
);

CREATE TABLE users_roles
(
    user_id BIGINT  NOT NULL,
    role_id TINYINT NOT NULL,
    CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT role_id FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE task_bar
(
    id    SMALLINT PRIMARY KEY AUTO_INCREMENT,
    title CHAR(60) NOT NULL
);

CREATE TABLE cards
(
    id          SMALLINT PRIMARY KEY AUTO_INCREMENT,
    title       CHAR(100) NOT NULL,
    description VARCHAR(255),
    file_url    VARCHAR(255),
    comment     VARCHAR(255),
    history     VARCHAR(100)
);


CREATE TABLE task_bar_cards
(
    task_bar_id SMALLINT NOT NULL,
    card_id     SMALLINT NOT NULL,
    CONSTRAINT task_bar_id FOREIGN KEY (task_bar_id) REFERENCES task_bar (id),
    CONSTRAINT card_id FOREIGN KEY (card_id) REFERENCES cards (id)
);

CREATE TABLE boards
(
    id                   TINYINT PRIMARY KEY AUTO_INCREMENT,
    title                VARCHAR(255)               NOT NULL,
    visibility           ENUM ('PUBLIC', 'PRIVATE') NOT NULL,
    background_image_url VARCHAR(255)
);