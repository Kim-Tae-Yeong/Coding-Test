import sys

n = int(input())
if(((3 * n) % 20) >= 10):
  cnt = ((3 * n) // 20) + 1
else:
  cnt = (3 * n) // 20

if(n == 0):
  ans = 0
else:
  tmp = []
  for _ in range(n):
    score = int(sys.stdin.readline().strip())
    tmp.append(score)
  tmp.sort()
  ans = sum(tmp[cnt : n - cnt])
  total = n - (2 * cnt)
  if((ans % total) >= (total / 2)):
    ans = (ans // total) + 1
  else:
    ans = ans // total

print(ans)