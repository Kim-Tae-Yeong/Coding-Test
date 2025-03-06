WITH CTE AS (
    SELECT
        CATEGORY,
        PRICE,
        PRODUCT_NAME,
        DENSE_RANK() OVER (PARTITION BY CATEGORY ORDER BY PRICE DESC) AS RN
    FROM
        FOOD_PRODUCT
)

SELECT
    CATEGORY,
    PRICE,
    PRODUCT_NAME
FROM
    CTE
WHERE
    RN = 1 AND
    CATEGORY IN (
        '과자',
        '국',
        '김치',
        '식용유'
    )
ORDER BY
    2 DESC