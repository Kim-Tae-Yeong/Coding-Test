with fish_length as (
    select
        coalesce(length, 10) as length
    from
        fish_info
)

select
    round(avg(length), 2) as average_length
from
    fish_length