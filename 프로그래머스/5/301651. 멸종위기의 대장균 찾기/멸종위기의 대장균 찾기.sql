with recursive ecoli_tree as (
    select id, parent_id, 1 as generation
    from ecoli_data
    where parent_id is null
    
    union all
    
    select ed.id, ed.parent_id, et.generation + 1
    from ecoli_data as ed
    join ecoli_tree as et on ed.parent_id = et.id
)

select count(*) as count, generation
from ecoli_tree
where id not in (
    select distinct parent_id
    from ecoli_data
    where parent_id is not null
)
group by generation
order by generation;