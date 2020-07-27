SELECT
	e1.NAME AS 'Employee',
	e1.Salary 
FROM
	Employee e1 
WHERE
	3 > ( SELECT count(  e2.Salary ) FROM Employee e2 WHERE e2.Salary > e1.Salary )
	
	
	
	
SELECT
    d.Name AS 'Department', e1.Name AS 'Employee', e1.Salary
FROM
    Employee e1
    LEFT JOIN
    Department d ON e1.DepartmentId = d.Id
WHERE
    3 > (SELECT
            COUNT( e2.Salary)
        FROM
            Employee e2
        WHERE
            e2.Salary > e1.Salary
                AND e1.DepartmentId = e2.DepartmentId
        );

	
