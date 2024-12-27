--liquibase formatted sql

-- changeset Sadip_Khadka:2_add_unique_constraint_email
-- preconditions:
--    - tableExists tableName=auth_users
--    - columnExists columnName=email tableName=auth_users
--    onFail:MARK_RAN
--    onError:HALT
ALTER TABLE auth_users
    ADD CONSTRAINT unique_email UNIQUE (email);