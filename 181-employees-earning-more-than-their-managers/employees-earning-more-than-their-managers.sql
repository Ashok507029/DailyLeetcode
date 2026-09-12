# Write your MySQL query statement below
SELECT e.name as Employee
FROM employee e join employee m on m.id = e.managerId
WHERE e.salary>m.salary;
