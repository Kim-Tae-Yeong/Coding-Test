SELECT
    T.ITEM_ID,
    (
        SELECT
             I.ITEM_NAME
        FROM
            ITEM_INFO AS I
        WHERE
            T.ITEM_ID = I.ITEM_ID
    ) AS ITEM_NAME
FROM
    ITEM_TREE AS T
WHERE
    T.PARENT_ITEM_ID IS NULL
ORDER BY
    1
