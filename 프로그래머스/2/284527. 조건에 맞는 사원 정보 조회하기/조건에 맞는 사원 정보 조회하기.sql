select
    g.score,
    e.emp_no,
    e.emp_name,
    e.position,
    e.email
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
        order by
            2 desc
        limit
            1
    ) as g
    on e.emp_no = g.emp_no