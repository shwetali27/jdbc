USE `test_schema1`;
DROP procedure IF EXISTS `add_numbers`;

DELIMITER $$
USE `test_schema1`$$
CREATE PROCEDURE `add_numbers`(
      IN in_value1 INTEGER,
      IN in_value2 INTEGER
)
BEGIN
	select (in_value1 + in_value2) as result;
END$$

DELIMITER ;