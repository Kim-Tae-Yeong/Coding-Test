import sys
import heapq as h

tmp = []

n = int(input())
ans = []

for _ in range(n):
  num = int(sys.stdin.readline().strip())
  if(num != 0):
    h.heappush(tmp, num)
  else:
    if(len(tmp) == 0):
      ans.append(str(0))
    else:
      ans.append(str(h.heappop(tmp)))

print("\n".join(ans))