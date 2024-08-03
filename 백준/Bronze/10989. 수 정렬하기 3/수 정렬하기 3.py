import sys

n = int(input())
ans = [0 for _ in range(10001)]
for _ in range(n):
  num = int(sys.stdin.readline().strip())
  ans[num] += 1

for idx, elem in enumerate(ans):
  if(elem == 0):
    continue
  for _ in range(elem):
    print(idx)