-- insert initial test data
-- the id is hardcode to enable references between further test data
INSERT INTO owner (ID, NAME, CREATED_AT, UPDATED_AT)
VALUES (1, 'Fred', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (2, 'Julia', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (3, 'Kim', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO horse (ID, NAME, NOTES, RATING, BIRTHDAY, CREATED_AT, UPDATED_AT)
VALUES  (1, 'Jackie', 'This is a note', '4', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
        (2, 'Madi', 'This is a note', '1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
        (3, 'Julie', '', '2', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

