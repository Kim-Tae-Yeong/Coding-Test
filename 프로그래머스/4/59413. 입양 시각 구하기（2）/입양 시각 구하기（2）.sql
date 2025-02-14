with recursive time as (
    select 0 as hour
    union all
    select hour + 1
    from time
    where hour < 23
)

select
    t.hour as hour,
    count(a.animal_id) as count
from
    time as t
    left join animal_outs as a
    on t.hour = hour(a.datetime)
group by
    t.hour
order by
    t.hour;