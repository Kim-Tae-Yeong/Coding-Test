import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/9095/9095.txt', 'rt')

t = int(sys.stdin.readline().strip())
ans = []
for _ in range(t):
  n = int(sys.stdin.readline().strip())
  tmp = [0 for _ in range(n + 1)]
  if(n == 1):
    ans.append(str(1))
  elif(n == 2):
    ans.append(str(2))
  elif(n == 3):
    ans.append(str(4))
  else:
    tmp[1] = 1
    tmp[2] = 2
    tmp[3] = 4
    for i in range(4, n + 1):
      tmp[i] = 4 * tmp[i - 3] + 2 * (tmp[i - 2] - tmp[i - 3]) + (tmp[i - 1] - tmp[i - 2] - tmp[i - 3])
    ans.append(str(tmp[-1]))

print('\n'.join(ans))