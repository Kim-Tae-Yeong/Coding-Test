## Design

- num list -> index
- dic에 {genre : [play 총 횟수, [[index, 해당 index의 play], [index, 해당 index의 play], ...]]}로 저장
- dic의 value만 list로 만든 다음, 총 play 횟수를 기준으로 내림차순 정렬 ( = -x[0])
- list의 각 원소의 첫 번째 원소( = [[index, 해당 index의 play], [index, 해당 index의 play], ...])를 각 index의 play 횟수를 기준으로 내림차순 정렬 ( = -x[1])
- 가장 많이 play 된 index부터 추가
