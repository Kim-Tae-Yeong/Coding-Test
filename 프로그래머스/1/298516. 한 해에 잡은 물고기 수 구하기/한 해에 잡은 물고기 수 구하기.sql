SELECT
    SUM(
       CASE
            WHEN YEAR(TIME) = 2021 THEN 1
            ELSE 0
        END
    ) AS FISH_COUNT
FROM
    FISH_INFO