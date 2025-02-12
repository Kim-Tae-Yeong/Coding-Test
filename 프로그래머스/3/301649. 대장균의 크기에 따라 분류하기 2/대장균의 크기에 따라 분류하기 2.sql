with RankedEcoli as (
    select
        id,
        ntile(4) over (order by size_of_colony desc) as RankGroup
    from ecoli_data
)
select
    id,
    case
        when RankGroup = 1 then 'CRITICAL'
        when RankGroup = 2 then 'HIGH'
        when RankGroup = 3 then 'MEDIUM'
        else 'LOW'
    end as colony_name
from RankedEcoli
order by id;