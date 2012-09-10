--
-- CRM-11 : Add the possibility to anonymize the CRM user
--
ALTER TABLE crm_user ADD COLUMN status SMALLINT DEFAULT 1 NOT NULL;
