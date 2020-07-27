SELECT
	customers.NAME AS 'Customers' 
FROM
	customers 
WHERE
	customers.id NOT IN ( SELECT customerid FROM orders );
	
	
	
	SELECT * FROM 
	customers ca
	WHERE ca.Id NOT IN ( SELECT customerId FROM orders)
	 