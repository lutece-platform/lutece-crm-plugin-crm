

--
-- ALTER TABLE crm_user
--

ALTER TABLE crm_user ADD COLUMN must_be_updated SMALLINT DEFAULT 0 NOT NULL;
ALTER TABLE crm_user ADD UNIQUE (user_guid);
CREATE INDEX  index_crm_user on crm_user(user_guid);
	