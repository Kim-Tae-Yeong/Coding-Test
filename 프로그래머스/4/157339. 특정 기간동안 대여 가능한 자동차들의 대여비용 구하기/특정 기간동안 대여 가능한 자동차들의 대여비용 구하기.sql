select
    c.car_id,
    c.car_type,
    floor(c.daily_fee * (1 - d.discount_rate / 100)) * 30 as fee
from
    car_rental_company_car as c
    join car_rental_company_rental_history as r
    on c.car_id = r.car_id
    join car_rental_company_discount_plan as d
    on c.car_type = d.car_type
where
    c.car_type in ('세단', 'suv')
    and d.duration_type = '30일 이상'
    and c.car_id not in (
        select
            car_id
        from
            car_rental_company_rental_history
        where
            end_date < '2022-11-01' or start_date > '2022-11-30'
    )
having
    fee between 500000 and 2000000 - 1
order by
    fee desc,
    c.car_type,
    c.car_id desc;
    
SELECT DISTINCT C.CAR_ID, C.CAR_TYPE, FLOOR(30*DAILY_FEE*(1 - DISCOUNT_RATE/100)) AS FEE
    FROM CAR_RENTAL_COMPANY_CAR AS C
    JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY AS H
      ON C.CAR_ID = H.CAR_ID 
    JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN AS D
      ON C.CAR_TYPE = D.CAR_TYPE AND
         D.DURATION_TYPE = '30일 이상'
    WHERE C.CAR_TYPE IN ('세단', 'SUV') 
          AND C.CAR_ID NOT IN (SELECT CAR_ID 
                                 FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                                WHERE NOT (END_DATE < '2022-11-01' OR 
                                           '2022-11-30' < START_DATE))
    HAVING FEE BETWEEN 500000 AND 2000000-1
    ORDER BY 3 DESC, 2, 1 DESC;