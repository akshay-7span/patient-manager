CREATE TABLE `treatment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `disease` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `medicine` varchar(255) DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  `resolve` bit(1) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `symptoms` varchar(255) DEFAULT NULL,
  `x_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_de2dhsi3d94s3tdol1rfje338` (`x_id`)
);