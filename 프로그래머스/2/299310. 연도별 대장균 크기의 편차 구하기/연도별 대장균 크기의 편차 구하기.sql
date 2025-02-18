with max_ecoli as (
    select
        year(differentiation_date) as year,
        max(size_of_colony) as max_size
    from
        ecoli_data
    group by
        year
)

select
    year(differentiation_date) as year,
    (m.max_size - e.size_of_colony) as year_dev,
    e.id
from
    ecoli_data as e
    join max_ecoli as m
    on year(e.differentiation_date) = m.year
order by
    1,
    2