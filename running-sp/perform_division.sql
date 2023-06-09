USE `test_schema1`;
DROP procedure IF EXISTS `perform_division`;

DELIMITER $$
USE `test_schema1`$$
CREATE PROCEDURE `perform_division`(
      IN in_dividend INTEGER,
      IN in_divisor INTEGER,
      OUT out_result varchar(20)
)
BEGIN
	IF (0 != in_divisor) THEN
		BEGIN
			SET out_result = in_dividend / in_divisor;
		END;
	ELSE
		BEGIN
			SET out_result = CONCAT("Invalid divisor: ", in_divisor);
		END;
    END IF;
END$$

DELIMITER ;