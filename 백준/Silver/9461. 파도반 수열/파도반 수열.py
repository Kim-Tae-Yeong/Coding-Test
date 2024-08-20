import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/9461/9461.txt', 'rt')

t = int(sys.stdin.readline().strip())
ans = []
for _ in range(t):
  n = int(sys.stdin.readline().strip())
  if(n <= 3):
    ans.append(str(1))
  elif(n <= 5):
    ans.append(str(2))
  else:
    dp = [0 for _ in range(n + 1)]
    dp[1] = 1
    dp[2] = 1
    dp[3] = 1
    dp[4] = 2
    dp[5] = 2
    for i in range(6, n + 1):
      dp[i] = dp[i - 1] + dp[i - 5]
    ans.append(str(dp[-1]))

print('\n'.join(ans))