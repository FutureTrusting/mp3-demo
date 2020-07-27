#方法：使用 JOIN 和 IN 语句
SELECT MAX(Salary) AS `Salary`,
DepartmentId
FROM Employee
GROUP BY DepartmentId


SELECT
    Department.name AS 'Department',
    Employee.name AS 'Employee',
    Salary
FROM
    Employee
    LEFT JOIN
    Department ON Employee.DepartmentId = Department.Id
WHERE
    (Employee.DepartmentId , Salary) IN
    (   SELECT
            DepartmentId, MAX(Salary)
        FROM
            Employee
        GROUP BY DepartmentId
	)
	