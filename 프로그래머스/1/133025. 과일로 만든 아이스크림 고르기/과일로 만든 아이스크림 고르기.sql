select f.flavor
from first_half as f inner join icecream_info as i on f.flavor = i.flavor
where total_order >= 3000 and ingredient_type = 'fruit_based';