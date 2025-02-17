select
    distinct d.id,
    d.email,
    d.first_name,
    d.last_name
from
    developers as d
    join skillcodes as s
    on d.skill_code & s.code = s.code
where
    s.name in (
        'python',
        'c#'
    )
order by
    1