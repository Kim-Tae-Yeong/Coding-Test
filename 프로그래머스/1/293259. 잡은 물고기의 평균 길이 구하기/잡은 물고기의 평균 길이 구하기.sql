select
    round(avg(
        case
            when length is null then 10
            else length
            end
    ), 2) as average_length
from
    fish_info