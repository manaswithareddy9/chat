CREATE TABLE IF NOT EXISTS users
(
    id BIGSERIAL NOT NULL,
    created_at timestamp without time zone NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_users_user_name UNIQUE (user_name)
);

INSERT INTO users(
	created_at, first_name, full_name, last_name, updated_at, user_name)
	VALUES 
	(CURRENT_TIMESTAMP, 'first1', 'first1 last1', 'last1', CURRENT_TIMESTAMP, 'first1.last1'),
	(CURRENT_TIMESTAMP, 'first2', 'first2 last2', 'last2', CURRENT_TIMESTAMP, 'first2.last2'),
	(CURRENT_TIMESTAMP, 'first3', 'first3 last3', 'last3', CURRENT_TIMESTAMP, 'first3.last3'),
	(CURRENT_TIMESTAMP, 'first4', 'first4 last4', 'last4', CURRENT_TIMESTAMP, 'first4.last4'),
	(CURRENT_TIMESTAMP, 'first5', 'first5 last5', 'last5', CURRENT_TIMESTAMP, 'first5.last5');

