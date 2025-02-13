select
    i.ingredient_type,
    sum(total_order) as total_order
from
    first_half as f
    join
    icecream_info as i
    on
    f.flavor = i.flavor
group by
    i.ingredient_type
order by
    sum(total_order);
    