drop table if exists account;
CREATE TABLE IF NOT EXISTS account (
                                       id  int(11) NOT NULL AUTO_INCREMENT,
                                       email VARCHAR(255) NOT NULL,
                                       name VARCHAR(255) NOT NULL default '',
                                       phone_number VARCHAR(255) NOT NULL,
                                       confirmed_and_active BOOLEAN NOT NULL DEFAULT false,
                                       member_since TIMESTAMP NOT NULL default current_timestamp,
                                       password_hash VARCHAR(100) default '',
                                       photo_url VARCHAR(255) NOT NULL,
                                       support BOOLEAN NOT NULL DEFAULT false,
                                       PRIMARY KEY (id),
                                       key ix_account_email (email),
                                       key ix_account_phone_number (phone_number)
) ENGINE=InnoDB;