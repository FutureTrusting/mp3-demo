#方法 1：使用 WHERE 语句
SELECT
    a.Name AS 'Employee'
FROM
    Employee AS a,
    Employee AS b
WHERE
    a.ManagerId = b.Id
AND a.Salary > b.Salary


#方法 2：使用 JOIN 语句
SELECT
     a.NAME AS Employee
FROM Employee AS a 
JOIN Employee AS b
     ON a.ManagerId = b.Id
     AND a.Salary > b.Salary





