select
    i.item_id,
    i.item_name,
    i.rarity
from
    item_tree as t
    join item_info as i
    on t.item_id = i.item_id
where
    parent_item_id in (
        select
            item_id
        from
            item_info
        where
            rarity = 'rare'
    )
order by
    1 desc