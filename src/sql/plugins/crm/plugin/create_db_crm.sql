--
-- Structure for table crm_demand_type
--
DROP TABLE IF EXISTS crm_demand_type;
CREATE TABLE crm_demand_type (
	id_demand_type INT(11) DEFAULT 0 NOT NULL,
	label VARCHAR(255) DEFAULT '' NOT NULL,
	url_resource VARCHAR(255) DEFAULT '' NOT NULL,
	url_info VARCHAR(255) DEFAULT '' NOT NULL,
	url_contact VARCHAR(255) DEFAULT '' NOT NULL,
	demand_type_order INT(11) DEFAULT 1 NOT NULL,
	id_category INT(11) DEFAULT 0 NOT NULL,
	date_begin DATE DEFAULT NULL,
	date_end DATE DEFAULT NULL,
	workgroup_key VARCHAR(50) DEFAULT '' NOT NULL,
	role_key VARCHAR(50) DEFAULT '' NOT NULL,
	PRIMARY KEY (id_demand_type) 
);

--
-- Structure for table crm_demand
--
DROP TABLE IF EXISTS crm_demand;
CREATE TABLE crm_demand (
	id_demand INT(11) DEFAULT 0 NOT NULL,
	id_demand_type INT(11) DEFAULT 0 NOT NULL,
	user_guid VARCHAR(255) DEFAULT '' NOT NULL,
	status_text VARCHAR(255) DEFAULT '' NOT NULL,
	id_status_crm INT(11) DEFAULT 0 NOT NULL,
	data VARCHAR(255) DEFAULT '' NOT NULL,
	date_modification TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL,
	PRIMARY KEY (id_demand)
);

--
-- Structure for table crm_notification
--
DROP TABLE IF EXISTS crm_notification;
CREATE TABLE crm_notification (
	id_notification INT(11) DEFAULT 0 NOT NULL,
	id_demand INT(11) DEFAULT 0 NOT NULL,
	status INT(11) DEFAULT 0 NOT NULL,
	object VARCHAR(255) DEFAULT '' NOT NULL,
	message VARCHAR(255) DEFAULT '' NOT NULL,
	date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (id_notification)
);

--
-- Structure for table crm_category
--
DROP TABLE IF EXISTS crm_category;
CREATE TABLE crm_category (
	id_category INT(11) DEFAULT 0 NOT NULL,
	name VARCHAR(50) NOT NULL DEFAULT '',
	description VARCHAR(255) NOT NULL DEFAULT '',
	PRIMARY KEY (id_category)
);

--
-- Structure for table crm_category
--
DROP TABLE IF EXISTS crm_status_crm;
CREATE TABLE crm_status_crm (
	id_status_crm INT(11) DEFAULT 0 NOT NULL,
	status_label VARCHAR(50) NOT NULL DEFAULT '',
	PRIMARY KEY (id_status_crm)
);
