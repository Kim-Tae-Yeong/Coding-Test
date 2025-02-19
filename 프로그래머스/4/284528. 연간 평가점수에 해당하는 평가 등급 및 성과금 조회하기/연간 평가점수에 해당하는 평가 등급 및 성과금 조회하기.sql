select
    e.emp_no,
    e.emp_name,
    case
        when g.score / 2 >= 96 then 'S'
        when g.score / 2 >= 90 then 'A'
        when g.score / 2 >= 80 then 'B'
        else 'C'
    end as grade,
    case
        when g.score / 2 >= 96 then e.sal * 0.2
        when g.score / 2 >= 90 then e.sal * 0.15
        when g.score / 2 >= 80 then e.sal * 0.1
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
    ) as g
    on e.emp_no = g.emp_no
order by
    1