import sys
from collections import deque

n, d, k ,c = map(int, input().split())
num = []
for _ in range(n):
  num.append(int(sys.stdin.readline().strip()))

tmp = deque(num[: k])
candidate = set(tmp)
ans = len(candidate)
contain = 0
for elem in tmp:
  if(elem == c):
    contain += 1
if(contain == 0):
  ans += 1

for i in range(k, n + k - 1):
  left = tmp.popleft()
  if(left == c):
    contain -= 1
  tmp.append(num[i % n])
  if(num[i % n] == c):
    contain += 1
  candidate = set(tmp)
  length = len(candidate)
  if(contain == 0):
    length += 1
  ans = max(ans, length)

print(ans)