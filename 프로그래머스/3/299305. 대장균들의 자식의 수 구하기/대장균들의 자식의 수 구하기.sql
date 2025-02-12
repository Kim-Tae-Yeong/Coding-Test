select id, (select count(*) from ecoli_data where parent_id = e.id) as child_count
from ecoli_data as e
order by id;

-- SELECT 
--     parent.ID, 
--     COUNT(child.ID) AS CHILD_COUNT
-- FROM 
--     ECOLI_DATA AS parent
-- LEFT JOIN 
--     ECOLI_DATA AS child
-- ON 
--     parent.ID = child.PARENT_ID
-- GROUP BY 
--     parent.ID
-- ORDER BY 
--     parent.ID;
