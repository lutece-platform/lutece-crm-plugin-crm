--
-- CRM-11 : Add the possibility to anonymize the CRM user
--
ALTER TABLE crm_user ADD COLUMN status SMALLINT DEFAULT 1 NOT NULL;

--
-- Add new column to url to delete the demand
--
ALTER TABLE crm_demand_type ADD COLUMN url_delete VARCHAR(255) DEFAULT '';