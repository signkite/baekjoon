SELECT CODE, COUNT(CODE)
FROM (SELECT LEFT(PRODUCT_CODE, 2) CODE
      FROM PRODUCT) AS P
GROUP BY CODE