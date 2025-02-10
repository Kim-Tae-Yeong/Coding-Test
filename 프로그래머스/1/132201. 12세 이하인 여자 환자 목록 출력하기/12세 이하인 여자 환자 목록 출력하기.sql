-- COALESCE(tlno, 'NONE') : tlno가 null이면 'NONE' 반환(아니면 tlno 그대로 반환)
select pt_name, pt_no, gend_cd, age, COALESCE(tlno, 'NONE') as tlno
from patient
where age <= 12 and gend_cd = 'W'
order by age desc, pt_name;
