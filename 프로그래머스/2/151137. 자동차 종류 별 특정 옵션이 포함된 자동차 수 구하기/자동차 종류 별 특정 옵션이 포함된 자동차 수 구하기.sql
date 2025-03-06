SELECT
    CAR_TYPE,
    COUNT(CAR_ID) AS CARS
FROM
    CAR_RENTAL_COMPANY_CAR
WHERE
    OPTIONS LIKE '%통풍시트%' OR
    OPTIONS LIKE '%열선시트%' OR
    OPTIONS LIKE '%가죽시트%'
GROUP BY
    1
ORDER BY
    1