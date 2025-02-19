select
    b.author_id,
    a.author_name,
    b.category,
    sum(s.sales* b.price) as total_sales
from
    book as b
    join author as a
    on b.author_id = a.author_id
    join book_sales as s
    on b.book_id = s.book_id
where
    s.sales_date like '2022-01%'
group by
    b.author_id,
    a.author_name,
    b.category
order by
    1,
    3 desc