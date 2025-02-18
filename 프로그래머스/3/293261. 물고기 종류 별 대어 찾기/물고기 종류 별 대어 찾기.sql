with max_fish as (
    select
        fish_type,
        max(length) as length
    from
        fish_info
    group by
        fish_type
)

select
    i.id,
    n.fish_name,
    i.length
from
    fish_info as i
    join fish_name_info as n
    on i.fish_type = n.fish_type
where
    (i.fish_type, i.length) in (
        select
            *
        from
            max_fish
    )
order by
    1