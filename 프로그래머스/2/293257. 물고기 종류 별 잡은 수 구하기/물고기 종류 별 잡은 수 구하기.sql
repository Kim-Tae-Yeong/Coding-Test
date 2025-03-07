SELECT
    COUNT(I.ID) AS FISH_COUNT,
    N.FISH_NAME
FROM
    FISH_INFO AS I
    JOIN FISH_NAME_INFO AS N
    ON I.FISH_TYPE = N.FISH_TYPE
GROUP BY
    2
ORDER BY
    1 DESC