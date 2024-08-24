import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/11279/11279.txt', 'rt')
import heapq

n = int(sys.stdin.readline().strip())
h = []
for _ in range(n):
  x = int(sys.stdin.readline().strip())
  if(x == 0):
    if(len(h) == 0):
      print(0)
    else:
      print(-heapq.heappop(h))
  else:
    heapq.heappush(h, -x)