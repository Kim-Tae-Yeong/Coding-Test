-- select item_id from item_info where rarity = 'rare' : rare인 itme의 id 찾기
-- select item_id from item_tree where parent_item_id in ~ : rare인 item이 업그레이드 가능한 item의 id 찾기
-- select item_id, item_name, rarity from item_info where item_id in ~ : rare인 item이 업그레이드 가능한 item의 id로 해당 item의 정보 찾기
select item_id, item_name, rarity
from item_info
where item_id in (
    select item_id
    from item_tree
    where parent_item_id in (select item_id from item_info where rarity = 'rare')
)
order by item_id desc;
