-- 코드를 입력하세요
-- date_format(column, '%Y-%m-%d') : '%Y-%m-%d' 형식으로 날짜 출력(시간 제외)
-- month : date type에서 월만 확인
-- order by column : column 기준 오름차순 정렬(desc : 내림차순 정렬)
SELECT MEMBER_ID, MEMBER_NAME, GENDER, date_format(DATE_OF_BIRTH, '%Y-%m-%d') as DATE_OF_BIRTH from MEMBER_PROFILE where month(DATE_OF_BIRTH) = 3 and GENDER = "W" and TLNO is not null order by MEMBER_ID;
