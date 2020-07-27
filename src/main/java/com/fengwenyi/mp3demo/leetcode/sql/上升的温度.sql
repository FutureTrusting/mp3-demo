#方法：使用 JOIN 和 DATEDIFF() 子句

SELECT
	weather.id AS 'Id' 
FROM
	weather
	JOIN weather w ON DATEDIFF( weather.RecordDate, w.RecordDate ) = 1 
	AND weather.Temperature > w.Temperature;