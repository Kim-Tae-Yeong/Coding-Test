SELECT
    T.ITEM_ID,
    (
        SELECT
            I.ITEM_NAME
        FROM
            ITEM_INFO AS I
        WHERE
            T.ITEM_ID = I.ITEM_ID
    ) AS ITEM_NAME,
    (
        SELECT
            I.RARITY
        FROM
            ITEM_INFO AS I
        WHERE
            T.ITEM_ID = I.ITEM_ID
    ) AS RARITY
FROM
    ITEM_TREE AS T
WHERE
    ITEM_ID NOT IN (
        SELECT
            PARENT_ITEM_ID
        FROM
            ITEM_TREE
        WHERE
            PARENT_ITEM_ID IS NOT NULL
    )
ORDER BY
    1 DESC