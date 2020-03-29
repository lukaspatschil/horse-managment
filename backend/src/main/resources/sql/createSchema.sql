CREATE TABLE IF NOT EXISTS owner
(
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  created_at DATETIME     NOT NULL,
  updated_at DATETIME     NOT NULL
);

CREATE TABLE IF NOT EXISTS horse
(
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(255)    NOT NULL,
  race          ENUM('ARABIAN', 'MORGAN', 'PAINT', 'APPALOOSA')     NOT NULL,
  notes         VARCHAR(255),
  rating        INTEGER         NOT NULL CHECK(rating BETWEEN 1 AND 5),
  birthday      DATE            NOT NULL,
  created_at    DATETIME        NOT NULL,
  updated_at    DATETIME        NOT NULL,
  owner         BIGINT          NOT NULL REFERENCES owner(id),
  image         BLOB            NOT NULL,
  type          VARCHAR(255)    NOT NULL
);
