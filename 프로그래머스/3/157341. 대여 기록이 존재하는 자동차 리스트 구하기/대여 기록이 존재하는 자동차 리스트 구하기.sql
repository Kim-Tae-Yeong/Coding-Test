SELECT
    DISTINCT C.CAR_ID
FROM
    CAR_RENTAL_COMPANY_CAR AS C
    JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY AS H
    ON C.CAR_TYPE = '세단' AND
    C.CAR_ID = H.CAR_ID AND
    H.START_DATE LIKE '2022-10%'
ORDER BY
    1 DESC
    