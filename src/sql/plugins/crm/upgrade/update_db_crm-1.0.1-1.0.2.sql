--
-- Update crm_notification
--
ALTER TABLE crm_notification CHANGE object object VARCHAR(255) DEFAULT '' NOT NULL;
