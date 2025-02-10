-- REST_INFO as i inner join REST_REVIEW as r on i.REST_ID = r.REST_ID : 두 table을 REST_ID 기준으로 join
-- like '서울%' : 서울로 시작하는 경우
-- group by i.REST_ID : REST_ID마다 한 줄씩 결과 출력(group by를 사용하지 않으면 전체 평균 하나만 반환)
select i.REST_ID, i.REST_NAME, i.FOOD_TYPE, i.FAVORITES, i.ADDRESS, round(avg(r.REVIEW_SCORE), 2) as SCORE
from REST_INFO as i inner join REST_REVIEW as r on i.REST_ID = r.REST_ID
where ADDRESS like "서울%"
group by i.REST_ID
order by SCORE desc, FAVORITES desc;
