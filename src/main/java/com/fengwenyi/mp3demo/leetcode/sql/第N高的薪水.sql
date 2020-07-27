CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      # Write your MySQL query statement below.
        select(
        SELECT DISTINCT Salary FROM Employee ORDER BY Salary DESC LIMIT N,1
      )
  );
END