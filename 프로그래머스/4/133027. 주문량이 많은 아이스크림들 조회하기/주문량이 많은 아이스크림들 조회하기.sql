with july_total as (
    select
        flavor,
        sum(total_order) as total_order
    from
        july
    group by
        flavor
)

select
    h.flavor
from
    first_half as h
    join july_total as t
    on h.flavor = t.flavor
order by
    (h.total_order + t.total_order) desc
limit
    3