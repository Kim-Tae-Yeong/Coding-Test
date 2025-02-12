select id, (select count(*) from ecoli_data where parent_id = e.id) as child_count
from ecoli_data as e
order by id;