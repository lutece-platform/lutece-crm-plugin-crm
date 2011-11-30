--
-- CRM-8 : Add the possibility to define the target of the demand type links
--
ALTER TABLE crm_demand_type ADD COLUMN target SMALLINT DEFAULT 0 NOT NULL;
