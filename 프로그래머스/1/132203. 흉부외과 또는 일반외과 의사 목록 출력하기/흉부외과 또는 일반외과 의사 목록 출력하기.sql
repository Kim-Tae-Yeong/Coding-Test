select DR_NAME, DR_ID, MCDP_CD, date_format(hire_ymd, '%Y-%m-%d') as HIRE_YMD
from doctor
where mcdp_cd = 'cs' or mcdp_cd = 'gs'
order by hire_ymd desc, dr_name;