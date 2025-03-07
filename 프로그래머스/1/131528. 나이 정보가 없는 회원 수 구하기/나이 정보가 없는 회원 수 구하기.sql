SELECT
    SUM(
        CASE
            WHEN AGE IS NULL THEN 1
            ELSE 0
        END
    ) AS USERS
FROM
    USER_INFO