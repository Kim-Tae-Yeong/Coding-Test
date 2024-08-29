import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/2579/2579.txt', 'rt')

n = int(sys.stdin.readline().strip())
score = [0] * (n + 1)
for i in range(n):
  score[i + 1] = int(sys.stdin.readline().strip())

dp = [0] * (n + 1)
dp[1] = score[1]
for i in range(2, n + 1):
  dp[i] = max(dp[i - 2] + score[i], dp[i - 3] + score[i - 1] + score[i])

print(dp[-1])