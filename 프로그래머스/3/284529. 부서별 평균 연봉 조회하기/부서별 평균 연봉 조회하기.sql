select
    d.dept_id,
    d.dept_name_en,
    round(a.avg_sal, 0) as avg_sal
from
    hr_department as d
    join (
        select
            dept_id,
            avg(sal) as avg_sal
        from
            hr_employees
        group by
            dept_id
    ) as a
    on d.dept_id = a.dept_id
order by
    a.avg_sal desc;