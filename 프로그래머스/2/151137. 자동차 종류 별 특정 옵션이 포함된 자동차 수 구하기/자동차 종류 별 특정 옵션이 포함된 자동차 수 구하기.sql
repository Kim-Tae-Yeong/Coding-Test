with car as (
    select
        *
    from
        car_rental_company_car
    where
        options like '%통풍시트%' or
        options like '%가죽시트%' or
        options like '%열선시트%'
)

select
    car_type,
    count(*) as cars
from
    car
group by
    car_type
order by
    1