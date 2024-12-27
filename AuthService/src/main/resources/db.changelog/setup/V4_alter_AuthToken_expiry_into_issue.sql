--liquibase formatted sql

-- changeset Sadip_Khadka:1_alter_table_changing_column_expiry_Date_issueDate
-- preconditions:
--    - tableExists tableName=auth_tokens
--    - columnExists columnName=expiry_date
--    onFail:MARK_RAN
--    onError:HALT
ALTER TABLE auth_tokens RENAME COLUMN expiry_date TO issued_date;