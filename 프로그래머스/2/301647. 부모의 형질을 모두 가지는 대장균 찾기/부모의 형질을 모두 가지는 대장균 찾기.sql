select
    c.id,
    c.genotype,
    p.genotype as parent_genotype
from
    ecoli_data as p
    join ecoli_data as c
    on p.id = c.parent_id
where
    c.genotype & p.genotype = p.genotype
order by
    1