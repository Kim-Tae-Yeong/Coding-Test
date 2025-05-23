SELECT
    YEAR(S.SALES_DATE) AS YEAR,
    MONTH(S.SALES_DATE) AS MONTH,
    U.GENDER,
    COUNT(DISTINCT S.USER_ID) AS USERS
FROM
    ONLINE_SALE AS S
    JOIN USER_INFO AS U
    ON S.USER_ID = U.USER_ID AND
    U.GENDER IS NOT NULL
GROUP BY
    1,
    2,
    3
ORDER BY
    1,
    2,
    3