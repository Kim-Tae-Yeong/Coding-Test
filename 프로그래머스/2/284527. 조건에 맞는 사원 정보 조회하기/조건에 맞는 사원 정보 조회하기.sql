WITH CTE AS (
    SELECT
        EMP_NO,
        SUM(SCORE) AS SCORE
    FROM
        HR_GRADE
    WHERE
        YEAR = 2022
    GROUP BY
        1
    ORDER BY
        2 DESC
    LIMIT
        1
)

SELECT
    C.SCORE,
    E.EMP_NO,
    E.EMP_NAME,
    E.POSITION,
    E.EMAIL
FROM
    HR_EMPLOYEES AS E
    JOIN CTE AS C
    ON E.EMP_NO = C.EMP_NO