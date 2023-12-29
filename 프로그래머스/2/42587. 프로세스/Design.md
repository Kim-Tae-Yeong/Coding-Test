## Design

- priorities의 실행되지 않은 elem 중 우선순위가 가장 높은 원소를 찾음
- 해당 elem의 index를 board에 append 후 priorities에서의 값을 0으로 바꿈
  - board에는 priorities의 elem이 우선순위가 높은 순으로 들어있음
  - 값을 0으로 바꿈으로써 해당 elem은 실행했음을 의미
- priorities의 모든 elem을 board에 append하면 board에서 location 값과 같은 elem의 (index + 1)을 return
- index 처리 시, "index % len(priorities)" 형태를 사용 (like linked list)
