with count as (
    select
        member_id,
        count(*) as count
    from
        rest_review
    group by
        member_id
    order by
        count desc
    limit 1
)

select
    m.member_name,
    r.review_text,
    date_format(r.review_date, '%Y-%m-%d') as review_date
from
    member_profile as m
    join rest_review as r
    on m.member_id = r.member_id
    join count as c
    on m.member_id = c.member_id
order by
    review_date,
    review_text