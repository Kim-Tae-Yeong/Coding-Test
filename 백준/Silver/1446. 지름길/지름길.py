import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/1446/1446.txt', 'rt')

n, d = map(int, sys.stdin.readline().strip().split())
info = []
for _ in range(n):
  s, e, l = map(int, sys.stdin.readline().strip().split())
  if(e > d or l > (e - s)):
    continue
  info.append([s, e, l])

dp = [i for i in range(d + 1)]
for i in range(d + 1):
  dp[i] = min(dp[i - 1] + 1, dp[i])
  for start, end, length in info:
    if(i == start and dp[i] + length < dp[end]):
      dp[end] = dp[i] + length

print(dp[-1])