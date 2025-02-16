with user_count as (
    select
        *
    from
        used_goods_board
    group by
        writer_id
    having
        count(*) >= 3
)

select
    u.user_id,
    u.nickname,
    concat(u.city, ' ', u.street_address1, ' ', u.street_address2) as '전체주소',
    CONCAT(SUBSTRING(u.TLNO, 1, 3), '-',
    SUBSTRING(u.TLNO, 4, 4), '-',
    SUBSTRING(u.TLNO, 8, 4)) AS 전화번호
from
    used_goods_user as u
    join user_count as c
    on u.user_id = c.writer_id
order by
    writer_id desc