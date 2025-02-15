select
    distinct d.id,
    d.email,
    d.first_name,
    d.last_name
from
    skillcodes as s
    join developers as d
    on d.skill_code & s.code = s.code
where
    s.category = 'front end'
order by
    d.id