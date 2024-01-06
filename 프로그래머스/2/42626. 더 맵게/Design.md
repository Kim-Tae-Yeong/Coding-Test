## Design

- import heapq as h로 heap 사용
- h.heapify(scoville) 주어진 배열을 최소 힙으로 만듬
- 힙의 맨 앞 원소( = 스코빌이 가장 낮은 음식)가 K보다 클때까지 반복문 실행
  - 만약 힙의 원소가 하나이면, -1 return
  - 그 외의 경우, answer을 +1하며 h.heappop를 이용해 스코빌이 가장 낮은 음식 2개를 선택
  - 그 2개를 이용해 새로운 음식을 만들고 h.heappush를 이용해 힙에 추가하고 최소 힙 성질 유지
  - 2개 원소를 제거하고 하나의 원소를 추가했으므로 length는 -1
