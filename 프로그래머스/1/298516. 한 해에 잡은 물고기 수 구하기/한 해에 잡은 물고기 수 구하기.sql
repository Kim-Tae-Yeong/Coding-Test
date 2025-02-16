select
    count(*) as fish_count
from
    fish_info
where
    year(time) like '2021%'