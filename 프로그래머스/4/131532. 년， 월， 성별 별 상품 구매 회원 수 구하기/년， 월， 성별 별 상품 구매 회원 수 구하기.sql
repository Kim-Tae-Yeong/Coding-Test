select
    year(o.sales_date) as year,
    month(o.sales_date) as month,
    u.gender as gender,
    count(distinct u.user_id) as users
from
    user_info as u
    join online_sale as o
    on u.user_id = o.user_id
where
    u.gender is not null
group by
    year(o.sales_date),
    month(o.sales_date),
    u.gender
order by
    year(o.sales_date),
    month(o.sales_date),
    u.gender;