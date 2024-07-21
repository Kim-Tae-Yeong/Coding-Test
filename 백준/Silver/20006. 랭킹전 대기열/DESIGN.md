level과 nickname을 입력값으로 받음

tmp 배열에는 각 방의 상태와 인원을 저장

  - tmp[i] (i = 0, 2, 4, ...) : Waiting! & Started!
    - 새로운 방을 만들면 Waiting!으로 시작
    - 이후 방의 정원이 1명이거나 인원이 추가되어 정원 수와 같게 되면 Started!로 변경
  - tmp[i + 1] : [현재 방의 인원, [생성자 level, 생성자 nickname], ...]

각 방의 인원과 생성자의 level을 기준으로 현재 방에 들어갈지, 새로운 방을 생성할 지 결정

이후 각 방의 인원을 nickname이 사전순으로 앞서는 인원부터 정렬
