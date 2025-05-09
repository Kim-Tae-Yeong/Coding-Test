WITH RECURSIVE CTE AS (
    SELECT
        0 AS HOUR
    UNION ALL
    SELECT
        HOUR + 1
    FROM
        CTE
    WHERE
        HOUR < 23
)

SELECT
    C.HOUR,
    COUNT(A.ANIMAL_ID) AS COUNT
FROM
    CTE AS C
    LEFT JOIN ANIMAL_OUTS AS A
    ON C.HOUR = HOUR(A.DATETIME)
GROUP BY
    1
ORDER BY
    1