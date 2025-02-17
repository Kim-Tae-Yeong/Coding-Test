select
    p.id,
    case
        when sum(c.id) is null then 0
        else count(*)
    end as child_count
from
    ecoli_data as p
    left join ecoli_data as c
    on p.id = c.parent_id
group by
    p.id
order by
    1