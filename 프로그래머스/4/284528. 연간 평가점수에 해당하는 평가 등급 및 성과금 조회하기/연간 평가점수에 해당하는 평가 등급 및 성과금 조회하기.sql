select
    e.emp_no,
    e.emp_name,
    case
        when a.score / 2 >= 96 then 'S'
        when a.score / 2 >= 90 then 'A'
        when a.score / 2 >= 80 then 'B'
        else 'C'
    end as grade,
    case
        when a.score / 2 >= 96 then sal * 0.2
        when a.score / 2 >= 90 then sal * 0.15
        when a.score / 2 >= 80 then sal * 0.1
        else 0
    end as bonus
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
    e.emp_no;