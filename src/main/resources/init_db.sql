CREATE TABLE `dao`.`manufactures` (
                                      `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                      `name` VARCHAR(255) NULL,
                                      `country` VARCHAR(100) NULL,
                                      `deleted` BIT NULL DEFAULT false,
                                      PRIMARY KEY (`id`));
CREATE TABLE `dao`.`drivers` (
                                 `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                 `name` VARCHAR(45) NULL,
                                 `license_number` VARCHAR(45) NULL,
                                 `deleted` VARCHAR(45) NULL DEFAULT 'false',
                                 `login` VARCHAR(45) NULL,
                                 `password` VARCHAR(45) NULL,
                                 PRIMARY KEY (`id`));

CREATE TABLE `dao`.`cars` (
                              `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                              `manufacturer_id` BIGINT(11) NULL,
                              `model` TEXT NULL,
                              `deleted` BIT NULL DEFAULT false,
                              PRIMARY KEY (`id`),
                              INDEX `manufacturer_idx` (`manufacturer_id` ASC) VISIBLE,
                              CONSTRAINT `manufacturer`
                                  FOREIGN KEY (`manufacturer_id`)
                                      REFERENCES `dao`.`manufactures` (`id`)
                                      ON DELETE NO ACTION
                                      ON UPDATE NO ACTION);
CREATE TABLE `dao`.`cars_drivers` (
                                      `driver_id` BIGINT(11) NULL,
                                      `car_id` BIGINT(11) NULL,
                                      INDEX `driver_idx` (`driver_id` ASC) VISIBLE,
                                      INDEX `car_idx` (`car_id` ASC) VISIBLE,
                                      CONSTRAINT `driver`
                                          FOREIGN KEY (`driver_id`)
                                              REFERENCES `dao`.`drivers` (`id`)
                                              ON DELETE NO ACTION
                                              ON UPDATE NO ACTION,
                                      CONSTRAINT `car`
                                          FOREIGN KEY (`car_id`)
                                              REFERENCES `dao`.`cars` (`id`)
                                              ON DELETE NO ACTION
                                              ON UPDATE NO ACTION);
