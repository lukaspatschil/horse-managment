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
  name          VARCHAR     NOT NULL,
  notes         VARCHAR,
  rating        INTEGER     NOT NULL,
  birthday      DATETIME    NOT NULL,
  created_at    DATETIME    NOT NULL,
  updated_at    DATETIME    NOT NULL
);
