SELECT 
    f.category,
    f.price AS max_price,
    f.product_name
FROM food_product f
JOIN (
    SELECT category, MAX(price) AS max_price
    FROM food_product
    WHERE category IN ('과자', '국', '김치', '식용유')
    GROUP BY category
) max_table 
ON f.category = max_table.category AND f.price = max_table.max_price
ORDER BY f.price DESC;