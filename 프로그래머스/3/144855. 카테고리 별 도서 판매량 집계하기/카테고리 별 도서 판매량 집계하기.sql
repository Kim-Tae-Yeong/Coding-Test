select
    b.category,
    sum(s.sales) as total_sales
from
    book as b
    join book_sales as s
    on b.book_id = s.book_id
where
    s.sales_date like '2022-01%'
group by
    b.category
order by
    1