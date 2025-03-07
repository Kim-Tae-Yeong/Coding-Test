WITH CTE AS (
    SELECT
        ID,
        FISH_TYPE,
        IFNULL(LENGTH, 10) AS LENGTH
    FROM
        FISH_INFO
)

SELECT
    COUNT(ID) AS FISH_COUNT,
    MAX(LENGTH) AS MAX_LENGTH,
    FISH_TYPE
FROM
    CTE
GROUP BY
    3
HAVING
    AVG(LENGTH) >= 33
ORDER BY
    3