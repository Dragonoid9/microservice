--liquibase formatted sql

-- changeset Sadip_Khadka:1_adding_email_user_table
-- preconditions:
--    - tableExists tableName=auth_users
--    onFail:MARK_RAN
--    onError:HALT
ALTER TABLE auth_users add column email varchar(255)