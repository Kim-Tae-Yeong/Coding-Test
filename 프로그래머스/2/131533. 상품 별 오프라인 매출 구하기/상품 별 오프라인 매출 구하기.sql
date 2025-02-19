select
    p.product_code,
    sum(s.sales_amount) * p.price as sales
from
    product as p
    join offline_sale as s
    on p.product_id = s.product_id
group by
    p.product_id,
    p.product_code
order by
    2 desc,
    1