with max_review as (
    select
        member_id,
        count(*) as count
    from
        rest_review
    group by
        member_id
    order by
        2 desc
    limit
        1
)

select
    m.member_name,
    r.review_text,
    date_format(r.review_date, '%Y-%m-%d') as review_date
from
    member_profile as m
    join rest_review as r
    on m.member_id = r.member_id
where
    r.member_id in (
        select
            member_id
        from
            max_review
    )
order by
    3,
    2