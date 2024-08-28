import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/30804/30804.txt', 'rt')

n = int(sys.stdin.readline().strip())
board = list(map(int, sys.stdin.readline().strip().split()))

if(n == 1):
  print(1)
else:
  left = 0
  max_length = 0
  count = {}

  for right in range(n):
    if(board[right] in count):
      count[board[right]] += 1
    else:
      count[board[right]] = 1

    while(len(count) > 2):
      count[board[left]] -= 1
      if(count[board[left]] == 0):
        del count[board[left]]
      left += 1

    max_length = max(max_length, right - left + 1)

  print(max_length)