import sys
# sys.stdin = open('/Users/kimtaeyeong/CodingTest/BaekJoon/1931/1931.txt', 'rt')

n = int(sys.stdin.readline().strip())
board = []
last_time = -1
for _ in range(n):
  s, e = map(int, sys.stdin.readline().strip().split())
  if(e > last_time):
    last_time = e
  board.append([s, e])
board.sort(key = lambda x : (-x[1], -x[0]))

dp = [0] * (last_time + 1)
for i in range(last_time + 1):
  if(i != 0):
    dp[i] = dp[i - 1]
  while True:
    if(len(board) == 0 or board[-1][-1] != i):
      break
    tmp = board.pop()
    start = tmp[0]
    end = tmp[1]
    dp[end] = max(dp[i], dp[start] + 1)

print(dp[-1])