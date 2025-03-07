WITH CTE AS (
    SELECT
        FLAVOR,
        SUM(TOTAL_ORDER) AS TOTAL_ORDER
    FROM
        JULY
    GROUP BY
        1
)

SELECT
    F.FLAVOR
FROM
    FIRST_HALF AS F
    JOIN CTE AS C
    ON F.FLAVOR = C.FLAVOR
GROUP BY
    1
ORDER BY
    F.TOTAL_ORDER + C.TOTAL_ORDER DESC
LIMIT
    3