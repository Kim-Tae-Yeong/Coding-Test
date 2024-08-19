import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/11501/11501.txt', 'rt')

t = int(sys.stdin.readline().strip())
ans = []
for _ in range(t):
  n = int(sys.stdin.readline().strip())
  day = list(map(int, sys.stdin.readline().strip().split()))
  score = 0
  max_num = day[-1]
  for i in range(n - 2, -1, -1):
    if(day[i] < max_num):
      score += (max_num - day[i])
    else:
      max_num = day[i]
  ans.append(str(score))

print('\n'.join(ans))