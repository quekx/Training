select
    d.name as Department,
    e.name as Employee,
    e.salary as Salary
from Employee e
         join
     (
         select
             rank() over(partition by departmentId order by salary desc) as r,
                 salary,
             departmentId
         from (
                  select salary, departmentId from Employee group by departmentId, salary
              ) s
     ) a
     on e.departmentId = a.departmentId and e.salary = a.salary
         join
     Department d
     on e.departmentId = d.id
where a.r <= 3
;