최초에 max_visitor와 tmp 값을 1일부터 x일까지의 방문자 수로 설정

이후 tmp에 하루씩 지날때마다 변경되는 방문자 수를 저장

tmp와 max_visitor가 같으면, day를 하루 증가

tmp가 더 크면( = 최대 방문자 수가 갱신되면), max_visitor를 tmp로 변경해주고 day를 1로 바꿈
