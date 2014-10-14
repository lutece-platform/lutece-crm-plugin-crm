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
	target SMALLINT DEFAULT 0 NOT NULL,
	url_delete VARCHAR(255) DEFAULT '',
	is_include_id_user SMALLINT DEFAULT 0 NOT NULL,
	is_need_authentication SMALLINT DEFAULT 0 NOT NULL,
	is_need_validation SMALLINT DEFAULT 0 NOT NULL,
	PRIMARY KEY (id_demand_type) 
);

--
-- Structure for table crm_demand
--
DROP TABLE IF EXISTS crm_demand;
CREATE TABLE crm_demand (
	id_demand INT(11) DEFAULT 0 NOT NULL,
	id_demand_type INT(11) DEFAULT 0 NOT NULL,
	id_crm_user INT(11) DEFAULT 0 NOT NULL,
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
	is_read SMALLINT DEFAULT 0 NOT NULL,
	object VARCHAR(255) DEFAULT '' NOT NULL,
	message LONG VARCHAR,
	date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	sender VARCHAR(255) DEFAULT '' NOT NULL,
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

--
-- Structure for table crm_user
--
DROP TABLE IF EXISTS crm_user;
CREATE TABLE crm_user (
	id_crm_user INT(11) DEFAULT 0 NOT NULL,
	user_guid VARCHAR(255) DEFAULT '' NOT NULL,
	status SMALLINT DEFAULT 1 NOT NULL,
	last_login TIMESTAMP DEFAULT '1980-01-01',
	current_last_login TIMESTAMP DEFAULT '1980-01-01',
	must_be_updated SMALLINT DEFAULT 0 NOT NULL,
	PRIMARY KEY (id_crm_user, user_guid)
);

ALTER TABLE crm_user ADD UNIQUE (user_guid);
CREATE INDEX  index_crm_user on crm_user(user_guid);

--
-- Structure for table crm_user_attribute
--
DROP TABLE IF EXISTS crm_user_attribute;
CREATE TABLE crm_user_attribute (
	id_crm_user INT(11) DEFAULT 0 NOT NULL,
	user_attribute_key VARCHAR(255) DEFAULT '' NOT NULL,
	user_attribute_value VARCHAR(255) DEFAULT '' NOT NULL,
	PRIMARY KEY (id_crm_user, user_attribute_key)
);

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
-- Table structure for table crm_demand_type_portlet
--
DROP TABLE IF EXISTS crm_demand_type_portlet;
CREATE TABLE crm_demand_type_portlet(
	id_portlet int NOT NULL,
	id_category int NOT NULL,
	PRIMARY KEY (id_portlet,id_category)
);
