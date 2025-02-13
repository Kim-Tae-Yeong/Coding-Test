select f.id, n.fish_name, f.length
from fish_info as f
    join fish_name_info as n
    on f.fish_type = n.fish_type
where f.length = (
    select max(f2.length)
    from fish_info as f2
    where f2.fish_type = f.fish_type
);