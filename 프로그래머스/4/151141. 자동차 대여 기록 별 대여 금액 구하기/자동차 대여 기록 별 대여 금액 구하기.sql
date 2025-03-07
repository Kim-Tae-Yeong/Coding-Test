SELECT
    H.HISTORY_ID,
    CASE
        WHEN DATEDIFF(H.END_DATE, H.START_DATE) + 1 >= 90 THEN FLOOR(C.DAILY_FEE * (100 - (
            SELECT
                DISCOUNT_RATE
            FROM
                CAR_RENTAL_COMPANY_DISCOUNT_PLAN
            WHERE
                CAR_TYPE = '트럭' AND
                DURATION_TYPE LIKE '90%'
            )
        ) / 100 * (DATEDIFF(H.END_DATE, H.START_DATE) + 1))
        WHEN DATEDIFF(H.END_DATE, H.START_DATE) + 1 >= 30 THEN FLOOR(C.DAILY_FEE * (100 - (
            SELECT
                DISCOUNT_RATE
            FROM
                CAR_RENTAL_COMPANY_DISCOUNT_PLAN
            WHERE
                CAR_TYPE = '트럭' AND
                DURATION_TYPE LIKE '30%'
            )
        ) / 100 * (DATEDIFF(H.END_DATE, H.START_DATE) + 1))
        WHEN DATEDIFF(H.END_DATE, H.START_DATE) + 1 >= 7 THEN FLOOR(C.DAILY_FEE * (100 - (
            SELECT
                DISCOUNT_RATE
            FROM
                CAR_RENTAL_COMPANY_DISCOUNT_PLAN
            WHERE
                CAR_TYPE = '트럭' AND
                DURATION_TYPE LIKE '7%'
            )
        ) / 100 * (DATEDIFF(H.END_DATE, H.START_DATE) + 1))
        ELSE C.DAILY_FEE * (DATEDIFF(H.END_DATE, H.START_DATE) + 1)
    END AS FEE
FROM
    CAR_RENTAL_COMPANY_CAR AS C
    JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY AS H
    ON C.CAR_TYPE = '트럭' AND
    C.CAR_ID = H.CAR_ID
ORDER BY
    2 DESC,
    1 DESC