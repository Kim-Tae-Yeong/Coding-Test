select
    animal_id,
    name
from
    animal_ins
where
    intake_condition not in ('aged')
order by
    animal_id