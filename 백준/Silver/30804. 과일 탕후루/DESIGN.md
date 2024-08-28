left와 rigth 두 변수를 이용

  - right : 입력받은 배열의 원소를 하나씩 탐색하면서 count dictionary에 해당 원소가 몇 번 나왔는지 저장
  - left : right 변수가 원소를 하나씩 탐색할때마다, 반복문 실행
    - count의 길이가 3 이상이면( = 3개의 과일이 들어있으면), left에 해당하는 과일을 하나 뺌
    - 이후 해당 과일이 존재하지 않으면, count에서 삭제
