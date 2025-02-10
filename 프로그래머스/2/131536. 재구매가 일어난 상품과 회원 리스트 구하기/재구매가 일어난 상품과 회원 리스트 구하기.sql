-- user_id와 product_id로 그룹을 나눔
-- 이렇게만 하면 해당 user_id와 product_id가 몇 번 나왔는지 알 수 없음
-- 때문에 having count(*) >= 2를 사용해 2번 이상 나온 행만 출력
select user_id, product_id
from online_sale
group by user_id, product_id
having count(*) >= 2
order by user_id, product_id desc;
