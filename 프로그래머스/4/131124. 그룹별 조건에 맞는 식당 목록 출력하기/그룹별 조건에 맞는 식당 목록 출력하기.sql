WITH CTE AS (
    SELECT
        MEMBER_ID
    FROM
        REST_REVIEW
    GROUP BY
        1
    ORDER BY
        COUNT(REST_ID) DESC
    LIMIT
        1
)

SELECT
    (
        SELECT
            MEMBER_NAME
        FROM
            MEMBER_PROFILE AS M
        WHERE
            MEMBER_ID = (
                SELECT
                    MEMBER_ID
                FROM
                    CTE
            )
    ) AS MEMBER_NAME,
    R.REVIEW_TEXT,
    DATE_FORMAT(R.REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM
    REST_REVIEW AS R
    JOIN CTE AS C
    ON R.MEMBER_ID = C.MEMBER_ID
ORDER BY
    3,
    2