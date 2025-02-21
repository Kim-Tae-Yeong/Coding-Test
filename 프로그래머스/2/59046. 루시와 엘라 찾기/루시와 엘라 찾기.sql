SELECT
    ANIMAL_ID,
    NAME,
    SEX_UPON_INTAKE
FROM
    ANIMAL_INS
WHERE
    NAME IN (
        'LUCY',
        'ELLA',
        'PICKLE',
        'ROGAN',
        'SABRINA',
        'MITTY'
    )
ORDER BY
    1