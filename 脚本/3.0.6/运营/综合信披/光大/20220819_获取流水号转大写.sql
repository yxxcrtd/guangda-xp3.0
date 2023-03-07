-- 导出 函数 gd_test._NEXTINDEX 结构
DROP FUNCTION IF EXISTS `_NEXTINDEX`;
DELIMITER //
CREATE FUNCTION `_NEXTINDEX`(n varchar(32)) RETURNS int
NO SQL
BEGIN
DECLARE b_num INT;
DECLARE incr_num INT;
SET b_num = (SELECT begin_num FROM SYS_SEQUENCE WHERE name = upper(n));
SET incr_num = (SELECT incr FROM SYS_SEQUENCE WHERE name = upper(n));
UPDATE SYS_SEQUENCE SET begin_num = (b_num + incr_num) WHERE name = upper(n) ;
RETURN b_num + incr_num;
END//
DELIMITER ;
-- 导出 函数 gd_test._NEXTINDEXNEW 结构
DROP FUNCTION IF EXISTS `_NEXTINDEXNEW`;
DELIMITER //
CREATE FUNCTION `_NEXTINDEXNEW`(n varchar(32)) RETURNS int
NO SQL
BEGIN
DECLARE b_num INT;
DECLARE incr_num INT;
SET b_num = (SELECT begin_num FROM SYS_SEQUENCE_NEW WHERE name = upper(n));
SET incr_num = (SELECT incr FROM SYS_SEQUENCE_NEW WHERE name = upper(n));
UPDATE SYS_SEQUENCE_NEW SET begin_num = (b_num + incr_num) WHERE name = upper(n) ;
RETURN b_num + incr_num;
END//
DELIMITER ;
