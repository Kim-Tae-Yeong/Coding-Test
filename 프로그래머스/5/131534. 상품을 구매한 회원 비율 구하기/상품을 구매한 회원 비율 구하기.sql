select
    year(o.sales_date) as year,
    month(o.sales_date) as month,
    count(distinct o.user_id) as purchased_users,
    round(count(distinct o.user_id) / (
        select
            count(*)
        from
            user_info
        where
            year(joined) = '2021'
        ), 1) as purchased_ratio
from
    online_sale as o
where
    o.user_id in (
        select
            user_id
        from
            user_info
        where
            year(joined) = '2021'
    )
group by
    year(sales_date),
    month(sales_date)
order by
    year(sales_date),
    month(sales_date)