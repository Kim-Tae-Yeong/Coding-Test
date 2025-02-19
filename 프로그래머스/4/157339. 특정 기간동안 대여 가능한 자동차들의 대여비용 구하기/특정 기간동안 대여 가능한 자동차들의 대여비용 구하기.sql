select
    distinct c.car_id,
    c.car_type,
    round(c.daily_fee * (100 - d.discount_rate) / 100 * 30, 0) as fee
from
    car_rental_company_car as c
    join car_rental_company_rental_history as h
    on c.car_id = h.car_id
    join car_rental_company_discount_plan as d
    on c.car_type = d.car_type and
    d.duration_type = '30일 이상'
where
    c.car_type in ('세단', 'SUV')
group by
    car_id,
    car_type
having
    min(h.start_date) > '2022-11-30' or max(h.end_date) < '2022-11-01' and
    (fee >= 500000 and fee < 2000000)
order by
    3 desc,
    2,
    1 desc