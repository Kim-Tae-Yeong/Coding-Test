select
    h.flavor
from
    first_half as h
    join icecream_info as i
    on h.flavor = i.flavor
where
    h.total_order >= 3000 and
    i.ingredient_type = 'fruit_based'
order by
    h.total_order desc