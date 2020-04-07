
DROP TABLE users;

CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(128) NOT NULL,
  last_name varchar(128) NOT NULL,
  contact varchar(128) NOT NULL,
  guid varchar(255) NOT NULL,
  created_on datetime NOT NULL,
  modified_on datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UX_users_guid (guid)
);