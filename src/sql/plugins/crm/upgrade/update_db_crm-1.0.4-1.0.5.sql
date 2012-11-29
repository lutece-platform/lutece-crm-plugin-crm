--
-- Structure for table crm_parameter
--
DROP TABLE IF EXISTS crm_parameter;
CREATE TABLE crm_parameter (
	parameter_key VARCHAR(100) NOT NULL,
	parameter_value VARCHAR(100) NOT NULL,
	PRIMARY KEY (parameter_key)
);

--
-- Init table crm_parameter
--
INSERT INTO crm_parameter (parameter_key, parameter_value) VALUES ('displayDraft', 'true' );

ALTER TABLE crm_user ADD COLUMN last_login TIMESTAMP DEFAULT '1980-01-01';
ALTER TABLE crm_user ADD COLUMN current_last_login TIMESTAMP DEFAULT '1980-01-01';
