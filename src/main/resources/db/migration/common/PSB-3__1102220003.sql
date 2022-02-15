ALTER TABLE `treatment`
  ADD CONSTRAINT `FKjpqauh9f08891a82no3i8aq7o`
  FOREIGN KEY (`patient_id`)
  REFERENCES `patient` (`id`)
  ON DELETE CASCADE;