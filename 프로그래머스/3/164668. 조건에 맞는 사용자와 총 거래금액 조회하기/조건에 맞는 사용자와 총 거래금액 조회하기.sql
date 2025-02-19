select
    u.user_id,
    u.nickname,
    sum(b.price) as total_sales
from
    used_goods_board as b
    join used_goods_user as u
    on b.writer_id = u.user_id
where
    b.status = 'DONE'
group by
    u.user_id,
    u.nickname
having
    sum(b.price) >= 700000
order by
    3