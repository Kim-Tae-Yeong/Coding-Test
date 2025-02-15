select
    p.product_code,
    p.price * sum(o.sales_amount) as sales
from
    product as p
    join offline_sale as o
    on p.product_id = o.product_id
group by
    product_code
order by
    sales desc,
    p.product_code