CREATE TABLE IF NOT EXISTS chats (
    id BIGSERIAL NOT NULL,
    created_at timestamp without time zone NOT NULL,
    text VARCHAR(255) NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    from_user_contact_id bigint NOT NULL,
    to_user_contact_id bigint NOT NULL,
    CONSTRAINT chats_pkey PRIMARY KEY (id),
    CONSTRAINT fk49510l1srm3swbc3952gxp3b2 FOREIGN KEY (to_user_contact_id) REFERENCES public.user_contacts (id) ON DELETE CASCADE,
    CONSTRAINT fktdq0xq996cr96oylk684q5ogi FOREIGN KEY (from_user_contact_id) REFERENCES public.user_contacts (id) ON DELETE CASCADE
);

INSERT INTO chats(
        created_at, text, updated_at, from_user_contact_id, to_user_contact_id)
VALUES
    (CURRENT_TIMESTAMP, 'text1', CURRENT_TIMESTAMP, 1, 2),
    (CURRENT_TIMESTAMP, 'text2', CURRENT_TIMESTAMP, 1, 3),
    (CURRENT_TIMESTAMP, 'text3', CURRENT_TIMESTAMP, 1, 4),
    (CURRENT_TIMESTAMP, 'text4', CURRENT_TIMESTAMP, 1, 5),
    (CURRENT_TIMESTAMP, 'text1', CURRENT_TIMESTAMP, 5, 1);
