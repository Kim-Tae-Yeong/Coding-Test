import sys

p = int(input())
tmp = []

for _ in range(p):
  ans = [0 for _ in range(21)]
  cnt = 0
  height = sys.stdin.readline().strip().split()
  ans[1] = int(height[1])
  for i in range(2, 21):
    num = int(height[i])
    for j in range(1, i + 1):
      if(int(ans[j]) > num):
        for k in range(i, j, -1):
          ans[k] = ans[k - 1]
        cnt += (i - j)
        break
    ans[j] = num
  tmp.append(cnt)

for i in range(p):
  print(i + 1, tmp[i])