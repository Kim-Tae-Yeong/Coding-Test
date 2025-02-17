with recursive family as (
    select
        id,
        parent_id,
        1 as generation
    from
        ecoli_data
    where
        parent_id is null
    union all
    select
        t1.id,
        t1.parent_id,
        t2.generation + 1
    from
        ecoli_data as t1
        join family as t2
        on t1.parent_id = t2.id   
)

select
    count(distinct t1.id) as count,
    generation
from
    family as t1
    left join ecoli_data as t2
    on t1.id = t2.parent_id
where
    t2.id is null
group by
    generation
order by
    2