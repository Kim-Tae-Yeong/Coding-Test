WITH CTE AS (
    SELECT
        COUNT(ID) AS FISH_COUNT
    FROM
        FISH_INFO
    WHERE
        FISH_TYPE IN (
            SELECT
                FISH_TYPE
            FROM
                FISH_NAME_INFO
            WHERE
                FISH_NAME IN (
                    'BASS',
                    'SNAPPER'
                )
        )
    GROUP BY
        FISH_TYPE
)

SELECT
    SUM(FISH_COUNT) AS FISH_COUNT
FROM
    CTE