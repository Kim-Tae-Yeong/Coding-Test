with total_july as (
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
    join total_july as j
    on h.flavor = j.flavor
order by
    h.total_order + j.total_order desc
limit
    3