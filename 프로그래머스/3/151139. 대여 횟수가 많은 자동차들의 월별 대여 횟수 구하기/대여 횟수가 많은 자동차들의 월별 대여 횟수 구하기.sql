with car_count as (
    select
        car_id,
        count(*) as count
    from
        car_rental_company_rental_history
    where
        start_date >= '2022-08-01' and start_date <= '2022-11-01'
    group by
        car_id
    having
        count(*) >= 5
)

select
    month(h.start_date) as month,
    h.car_id,
    count(*) as records
from
    car_rental_company_rental_history as h
    join car_count as c
    on h.car_id = c.car_id
where
    h.start_date >= '2022-08-01' and h.start_date <= '2022-11-01'
group by
    month(h.start_date),
    h.car_id
order by
    1,
    2 desc