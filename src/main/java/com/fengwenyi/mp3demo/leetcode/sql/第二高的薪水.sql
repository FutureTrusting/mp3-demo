#方法一：使用子查询和 LIMIT 子句
SELECT DISTINCT Salary AS `SecondHighestSalary` FROM employee ORDER BY Salary DESC LIMIT 1 OFFSET 1

#然而，如果没有这样的第二最高工资，这个解决方案将被判断为 “错误答案”，因为本表可能只有一项记录。为了克服这个问题，我们可以将其作为临时表。
SELECT (SELECT DISTINCT Salary FROM employee ORDER BY Salary DESC LIMIT 1 OFFSET 1) AS `SecondHighestSalary`

#方法二：使用 IFNULL 和 LIMIT 子句
#解决 “NULL” 问题的另一种方法是使用 “IFNULL” 函数，如下所示。
SELECT
    IFNULL(
      (SELECT DISTINCT Salary
       FROM Employee
       ORDER BY Salary DESC
        LIMIT 1 OFFSET 1),
    NULL) AS SecondHighestSalary
