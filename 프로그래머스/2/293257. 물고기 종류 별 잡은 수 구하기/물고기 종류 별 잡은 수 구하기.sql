select
    count(i.id) as fish_count,
    n.fish_name
from
    fish_name_info as n
    join fish_info as i
    on n.fish_type = i.fish_type
group by
    n.fish_name
order by
    count(i.id) desc;