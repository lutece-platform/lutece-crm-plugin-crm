--
-- ALTER TABLE crm_demand
--
ALTER TABLE crm_demand  ADD COLUMN remote_id VARCHAR(255);
ALTER TABLE crm_demand ADD UNIQUE INDEX (id_demand_type,remote_id);



