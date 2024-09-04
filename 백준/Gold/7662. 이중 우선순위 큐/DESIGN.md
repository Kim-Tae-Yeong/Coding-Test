TreeMap 사용

TreepMap
- 특징
  1. key - value 쌍 관리
  2. 정렬된 순서 유지 (키 기준)
  3. 빠른 탐색, 삽입, 삭제
 
- 주요 메서드
  - put(key, value) : 지정된 key와 value를 TreeMap에 추가, 이미 키가 존재하면 그 키에 대응하는 값을 새 값으로 업데이트함
  - get(key) : 지정된 key에 대응하는 값 반환, 키가 존재하지 않으면 Null 반환
  - remove(key) : 지정된 key에 대응하는 key - value 쌍 제거
  - firstKey() : 가장 낮은(작은) key 반환
  - lastKey() : 가장 높은(큰) key 반환
