--liquibase formatted sql

-- changeset Sadip_Khadka:1_create_user_table
-- preconditions:
--    - tableNotExists tableName=auth_users
--    onFail:MARK_RAN
--    onError:HALT
CREATE TABLE auth_users (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            username VARCHAR(255) NOT NULL,
                            password VARCHAR(255),
                            UNIQUE (username)
);

-- changeset Sadip_Khadka:2_create_role_table
-- preconditions:
--    - tableNotExists tableName=auth_roles
--    onFail:MARK_RAN
--    onError:HALT
CREATE TABLE auth_roles (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

-- changeset Sadip_Khadka:3_create_user_role_mapping_table
-- preconditions:
--    - tableNotExists tableName=user_role
--    onFail:MARK_RAN
--    onError:HALT
CREATE TABLE user_role (
                           user_id BIGINT NOT NULL,
                           role_id BIGINT NOT NULL,
                           PRIMARY KEY (user_id, role_id),
                           CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES auth_users (id) ON DELETE CASCADE,
                           CONSTRAINT fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES auth_roles (id) ON DELETE CASCADE
);

-- changeset Sadip_Khadka:4_create_refresh_token_table
-- preconditions:
--    - tableNotExists tableName=auth_tokens
--    onFail:MARK_RAN
--    onError:HALT
CREATE TABLE auth_tokens (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             access_token VARCHAR(255) NOT NULL UNIQUE,
                             refresh_token VARCHAR(255) NOT NULL UNIQUE,
                             expiry_date TIMESTAMP NOT NULL,
                             user_id BIGINT NOT NULL,
                             used BOOLEAN DEFAULT FALSE,  -- Flag to track if token has been used
                             CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES auth_users (id) ON DELETE CASCADE
);
