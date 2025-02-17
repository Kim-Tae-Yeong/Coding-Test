select
    member_id,
    member_name,
    gender,
    date_format(date_of_birth, '%Y-%m-%d') as date_of_birth
from
    member_profile
where
    month(date_of_birth) = '03'
    and tlno is not null
    and gender = 'w'
order by
    member_id