import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/11727/11727.txt', 'rt')

n = int(sys.stdin.readline().strip())
if(n == 1):
  print(1)
elif(n == 2):
  print(3)
else:
  ans = [0 for _ in range(n + 1)]
  ans[1] = 1
  ans[2] = 3
  for i in range(3, n + 1):
    ans[i] = ans[i - 1] + 2 * ans[i - 2]
  print(ans[-1] % 10007)