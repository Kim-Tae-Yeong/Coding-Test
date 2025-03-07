WITH CTE AS (
    SELECT
        EMP_NO,
        AVG(SCORE) AS SCORE
    FROM
        HR_GRADE
    GROUP BY
        1
)

SELECT
    E.EMP_NO,
    E.EMP_NAME,
    CASE
        WHEN C.SCORE >= 96 THEN 'S'
        WHEN C.SCORE >= 90 THEN 'A'
        WHEN C.SCORE >= 80 THEN 'B'
        ELSE 'C'
    END AS GRADE,
    CASE
        WHEN C.SCORE >= 96 THEN E.SAL * 0.2
        WHEN C.SCORE >= 90 THEN E.SAL * 0.15
        WHEN C.SCORE >= 80 THEN E.SAL * 0.1
        ELSE 0
    END AS BONUS
FROM
    HR_EMPLOYEES AS E
    JOIN CTE AS C
    ON E.EMP_NO = C.EMP_NO
ORDER BY
    1