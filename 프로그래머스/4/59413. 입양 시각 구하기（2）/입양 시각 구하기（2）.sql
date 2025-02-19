with recursive time as (
    select
        0 as hour
    union all
    select
        hour + 1
    from
        time
    where
        hour < 23
)

select
    t.hour,
    count(o.animal_id) as count
from
    time as t
    left join animal_outs as o
    on t.hour = hour(o.datetime)
group by
    t.hour
order by
    1