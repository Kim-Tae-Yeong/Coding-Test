select
    a.score as score,
    e.emp_no as emp_no,
    e.emp_name as emp_name,
    e.position as position,
    e.email as email
from
    hr_employees as e
    join (
        select
            emp_no,
            sum(score) as score
        from
            hr_grade
        group by
            emp_no
    ) as a
    on e.emp_no = a.emp_no
order by
    a.score desc
limit 1;