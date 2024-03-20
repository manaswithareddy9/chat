CREATE TABLE IF NOT EXISTS user_contacts (
    id BIGSERIAL NOT NULL,
    created_at timestamp without time zone NOT NULL,
    email_id VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT user_contacts_pkey PRIMARY KEY (id),
    CONSTRAINT fkqgbpf3rh5b6i7npvr2rf776rd FOREIGN KEY (user_id) REFERENCES public.users (id) ON DELETE CASCADE
);

INSERT INTO user_contacts(
    created_at, email_id, phone_number, updated_at, user_id)
VALUES
    (CURRENT_TIMESTAMP,'test1@gmail.com','913-963-3191',CURRENT_TIMESTAMP,1),
    (CURRENT_TIMESTAMP,'test2@gmail.com','913-963-3192',CURRENT_TIMESTAMP,2),
    (CURRENT_TIMESTAMP,'test3@gmail.com','913-963-3193',CURRENT_TIMESTAMP,3),
    (CURRENT_TIMESTAMP,'test4@gmail.com','913-963-3194',CURRENT_TIMESTAMP,4),
    (CURRENT_TIMESTAMP,'test5@gmail.com','913-963-3195',CURRENT_TIMESTAMP,5);