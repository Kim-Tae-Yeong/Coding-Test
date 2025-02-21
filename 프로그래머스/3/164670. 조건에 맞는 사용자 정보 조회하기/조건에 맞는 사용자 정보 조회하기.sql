SELECT
    U.USER_ID,
    U.NICKNAME,
    CONCAT(
        U.CITY,
        ' ',
        U.STREET_ADDRESS1,
        ' ',
        U.STREET_ADDRESS2
    ) AS '전체주소',
    CONCAT(
        SUBSTRING(U.TLNO, 1, 3), '-',
        SUBSTRING(U.TLNO, 4, 4), '-',
        SUBSTRING(U.TLNO, 8, 4)
    ) AS 전화번호
FROM
    USED_GOODS_BOARD AS B
    JOIN USED_GOODS_USER AS U
    ON B.WRITER_ID = U.USER_ID
GROUP BY
    B.WRITER_ID
HAVING
    COUNT(*) >= 3
ORDER BY
    1 DESC