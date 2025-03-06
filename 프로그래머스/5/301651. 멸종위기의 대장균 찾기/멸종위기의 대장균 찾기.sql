WITH RECURSIVE CTE AS (
    SELECT
        ID,
        PARENT_ID,
        1 AS GENERATION
    FROM
        ECOLI_DATA
    WHERE
        PARENT_ID IS NULL
    
    UNION ALL
    
    SELECT
        C.ID,
        C.PARENT_ID,
        (P.GENERATION + 1) AS GENERATION
    FROM
        ECOLI_DATA AS C
        JOIN CTE AS P
        ON C.PARENT_ID = P.ID
)

SELECT
    COUNT(ID) AS COUNT,
    GENERATION
FROM
    CTE AS C1
WHERE
    ID NOT IN (
        SELECT
            C2.PARENT_ID
        FROM
            CTE AS C2
        WHERE
            C1.GENERATION + 1 = C2.GENERATION
    )
GROUP BY
    2