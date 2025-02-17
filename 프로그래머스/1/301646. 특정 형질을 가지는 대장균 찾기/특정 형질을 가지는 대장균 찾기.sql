select
    count(*) as count
from
    ecoli_data
where
    genotype & 2 != 2 and
    (genotype & 1 = 1 or genotype & 4 = 4)