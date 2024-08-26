drop table if exists activities CASCADE;
drop table if exists addresses CASCADE;
DROP TABLE IF EXISTS USERS CASCADE;
drop table if exists activity_user cascade;
drop table if exists address_user cascade;




CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       username VARCHAR(255),
                       password VARCHAR(255),
                       phone_number VARCHAR(15) NOT NULL,
                       enabled BOOLEAN NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       user_type smallint NOT NULL
);

CREATE TABLE ACTIVITIES
(
    ACTIVITY_ID  BIGSERIAL PRIMARY KEY,
    DATE         DATE     NOT NULL,
    START_TIME   TIME     NOT NULL,
    FINISH_TIME  TIME     NOT NULL,
    SERVICE_TYPE smallint NOT NULL,
    TASK_DONE    bool
);

CREATE TABLE ADDRESSES
(
    ADDRESS_ID   SERIAL PRIMARY KEY,
    STREET       VARCHAR(255),
    CITY         VARCHAR(255),
    POST_CODE    VARCHAR(10),
    ADDRESS_TYPE smallint NOT NULL
);

CREATE TABLE activity_user
(
    activity_id BIGINT,
    user_id   BIGINT,
    PRIMARY KEY (ACTIVITY_ID, user_id),
    FOREIGN KEY (ACTIVITY_ID) REFERENCES ACTIVITIES (ACTIVITY_ID),
    FOREIGN KEY (user_id) REFERENCES users (ID)

);

create table address_user
(
    ADDRESS_ID BIGINT,
    user_id  BIGINT,
    PRIMARY KEY (ADDRESS_ID, user_id),
    FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESSES (ADDRESS_ID),
    FOREIGN KEY (user_id) REFERENCES USERS (ID)
);

ALTER TABLE USERS ADD CONSTRAINT unique_email UNIQUE (EMAIL);

drop type if exists SERVICE_TYPE;
drop type if exists ADDRESS_TYPE;
drop type if exists USER_TYPE;

CREATE TYPE SERVICE_TYPE AS ENUM ('EYELASHES', 'EYEBROWS', 'PERMANENT_MAKEUP', 'FACIAL_TREATMENT', 'OTHER');
CREATE TYPE ADDRESS_TYPE AS ENUM ('HOME_ADDRESS', 'BILLING_ADDRESS' , 'DELIVERY_ADDRESS');
CREATE TYPE USER_TYPE AS ENUM ('CLIENT' , 'EMPLOYEE', 'ADMINISTRATOR');

