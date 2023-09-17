CREATE TABLE `crud_fedorovska`.`buildings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `building_number` INT NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `crud_fedorovska`.`flats` (
   `id` INT NOT NULL AUTO_INCREMENT,
  `flat_number` INT NOT NULL,
  `number_of_rooms` INT NOT NULL,
  `square` FLOAT NOT NULL,
  `building_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_building_id_idx` (`building_id` ASC) VISIBLE,
  CONSTRAINT `fk_building_id`
    FOREIGN KEY (`building_id`)
    REFERENCES `crud_fedorovska`.`buildings` (`id`));

  CREATE TABLE `crud_fedorovska`.`member_osbb` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `crud_fedorovska`.`residents_rc` (
   `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `tel` INT(10) NOT NULL,
  `e_mail` VARCHAR(45) NOT NULL,
  `entry_by_car` VARCHAR(5) NOT NULL,
  `member_osbb_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_member_osbb_resident_idx` (`member_osbb_id` ASC) VISIBLE,
  CONSTRAINT `fk_member_osbb_resident`
    FOREIGN KEY (`member_osbb_id`)
    REFERENCES `crud_fedorovska`.`member_osbb` (`id`));

    CREATE TABLE `crud_fedorovska`.`ownership` (
   `id` INT NOT NULL AUTO_INCREMENT,
  `flats_id` INT NOT NULL,
  `residents_RC_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ownership_residents_rc_id_idx` (`residents_rc_id` ASC) INVISIBLE,
  INDEX `fk_ownership_flat_id_idx` (`flats_id` ASC) INVISIBLE,
  CONSTRAINT `fk_ownership_flat_id`
    FOREIGN KEY (`flats_id`)
    REFERENCES `crud_fedorovska`.`flats` (`id`),
  CONSTRAINT `fk_ownership_residents_rc_id`
    FOREIGN KEY (`residents_rc_id`)
    REFERENCES `crud_fedorovska`.`residents_rc` (`id`));

    CREATE TABLE `crud_fedorovska`.`residence` (
   `id` INT NOT NULL AUTO_INCREMENT,
  `flats_id` INT NOT NULL,
  `member_osbb_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_residence_flat_id_idx` (`flats_id` ASC) INVISIBLE,
  INDEX `fk_residence_member_osbb_id_idx` (`member_osbb_id` ASC) INVISIBLE,
  CONSTRAINT `fk_residence_flat_id`
    FOREIGN KEY (`flats_id`)
    REFERENCES `crud_fedorovska`.`flats` (`id`),
  CONSTRAINT `fk_residence_member_osbb_id`
    FOREIGN KEY (`member_osbb_id`)
    REFERENCES `crud_fedorovska`.`member_osbb` (`id`));
