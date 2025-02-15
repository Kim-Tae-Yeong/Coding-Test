select
    concat(
        "/home/grep/src/",
        f.board_id,
        "/",
        f.file_id,
        f.file_name,
        f.file_ext
    ) as file_path
from
    used_goods_file as f
    join (
        select
            *
        from
            used_goods_board
        order by
            views desc
        limit
            1
    ) as b
    on f.board_id = b.board_id
order by
    file_path desc