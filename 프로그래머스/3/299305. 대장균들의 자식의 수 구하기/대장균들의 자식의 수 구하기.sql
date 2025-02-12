select parent.id, count(child.id) as child_count
from ecoli_data as parent left join ecoli_data as child on parent.id = child.parent_id
group by parent.id
order by parent.id;