입력받은 배열을 하나씩 탐색하면서 현재 원소가 'I'이면 현재 위치( = i - 1)부터 가능한 P(k) 중 가장 큰 k 값을 구함

  - s[i - 1] != s[i] 경우
    - s[i] == 'O'이면 k 값( = cnt) 1 증가
    - i가 배열의 마지막 위치인 경우
      - s[i] == 'I'이면 cnt 저장
        - ex) IOIO....IOI -> O가 cnt개 존재
      - s[i] == 'O'이면 (cnt - 1) 저장
        - ex) IOIO...IOIO -> O가 cnt개 존재하지만 P(cnt)는 불가능하므로 (cnt - 1) 저장
  - s[i - 1] == s[i]인 경우
    - s[i] == 'O'이면 (cnt - 1) 저장
      - ex) IOIO...IOO -> O가 cnt개 존재하지만 P(cnt)는 불가능하므로 (cnt - 1) 저장
      - 이후 그 다음에 나올 'I'를 찾아야하기 때문에 start = False
    - s[i] == 'I'이면 cnt 저장
      - ex) IOIO...IOII -> 0가 cnt개 존재
      - 이후 i부터 다시 가능한 P(k) 중 가장 큰 k 값을 찾아야 함
