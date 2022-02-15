ALTER TABLE patient
DROP COLUMN is_active;

ALTER TABLE patient
  ADD status varchar(255);