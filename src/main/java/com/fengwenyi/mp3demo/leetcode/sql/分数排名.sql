-- 方法: 自连接
-- 目的：对于一个成绩，查找出大于等于它的成绩（去重）的数量即为其排名，整理排序即得结果
SELECT 
    a.Score AS Score,
    COUNT(DISTINCT b.Score) AS Rank -- 注意按题目要求去重复值
FROM Scores AS a, Scores AS b
WHERE b.Score >= a.Score    -- 表b中有x个非重复值大于等于表a当前值，则表a当前成绩排名为x
GROUP BY a.id   -- 由于成绩即使重复也要显示，故通过id分组
ORDER BY a.Score DESC
