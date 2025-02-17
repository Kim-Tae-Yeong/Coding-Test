with ecoli_divided as (
    select
        id,
        ntile(4) over(order by size_of_colony desc) as num
    from
        ecoli_data
)

select
    id,
    case
        when num = 1 then 'CRITICAL'
        when num = 2 then 'HIGH'
        when num = 3 then 'MEDIUM'
        else 'LOW'
    end as colony_name
from
    ecoli_divided
order by
    1