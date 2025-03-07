WITH CTE AS (
    SELECT
        WRITER_ID
    FROM
        USED_GOODS_BOARD
    GROUP BY
        1
    HAVING
        COUNT(WRITER_ID) >= 3
)

SELECT
    U.USER_ID,
    U.NICKNAME,
    CONCAT(
        U.CITY,
        " ",
        U.STREET_ADDRESS1,
        " ",
        U.STREET_ADDRESS2
    ) AS '전체주소',
    CONCAT(
        SUBSTRING(U.TLNO, 1, 3),
        "-",
        SUBSTRING(U.TLNO, 4, 4),
        "-",
        SUBSTRING(U.TLNO, 8, 4)
    ) AS '전화번호'
FROM
    USED_GOODS_USER AS U
    JOIN CTE AS C
    ON U.USER_ID = C.WRITER_ID
ORDER BY
    1 DESC