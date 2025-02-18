select
    i.item_id,
    i.item_name
from
    item_info as i
    join (
        select
            *
        from
            item_tree
        where
            parent_item_id is null
    ) as t
    on i.item_id = t.item_id
order by
    1