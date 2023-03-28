-- Create table If Not Exists Employee (Id int, Salary int)
--     Truncate table Employee
--     insert into Employee (id, salary) values ('1', '100')
--     insert into Employee (id, salary) values ('2', '200')
--     insert into Employee (id, salary) values ('3', '300')

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    declare M INT;
    set M = N - 1;
RETURN (
        select distinct(salary) from Employee order by salary limit 1 offset M;
    );
END