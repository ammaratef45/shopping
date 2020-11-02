INSERT INTO oauth_client_details
 (
  client_id,
  client_secret,
  web_server_redirect_uri,
  scope,
  access_token_validity,
  refresh_token_validity,
  resource_ids,
  authorized_grant_types,
  additional_information
 )
 VALUES
 (
  'mobile',
  '{bcrypt}$2a$10$xBWazKM9I7Q42fAL2vOMy.aYTHEh5Ttr/tdnCCAHrm3/21AFMpZ32', /*pin*/
  'http://localhost:8084/code',
  'READ,WRITE',
  '3600',
  '10000',
  'inventory,payment',
  'authorization_code,password,refresh_token,implicit',
  '{}'
 );

INSERT INTO role (ID, NAME) VALUES
 (1, 'ROLE_admin'),
 (2, 'ROLE_user'),
 (3, 'ROLE_seller');

insert into user
 (id, username,password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked)
 VALUES
 (
  '1', 'Ammar',
  '{bcrypt}$2a$10$.iSjNGF8nlv9PSHQ5CAwEeGkRqfHbCENGq.e.dzb2SlY8RUqauBra', /*ammar*/
  'ammar@example.com', '1', '1', '1', '1'
 );
insert into  user
 (id, username,password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked)
 VALUES
 (
  '2', 'Abu',
  '{bcrypt}$2a$10$Z1WuJ19/Dm3LOU1zL4UrbOvVdfjPaDlBBXVsB8I1tqtbEveqJVtKa', /*abu*/
  'abu@example.com', '1', '1', '1', '1'
 );

INSERT INTO ROLE_USER (ROLE_ID, USER_ID)
    VALUES
    (1, 1) /* Ammar-admin */,
    (2, 3) /* Abu-seller */ ;