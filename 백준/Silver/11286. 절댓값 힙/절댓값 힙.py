import sys
# sys.stdin = open('/Users/kimtaeyeong/CodingTest/BaekJoon/11286/11286.txt', 'r')
import heapq

n = int(sys.stdin.readline().strip())
h = []
for _ in range(n):
  x = int(sys.stdin.readline().strip())
  if(x != 0):
    heapq.heappush(h, [abs(x), x])
  else:
    if(len(h) != 0):
      print(heapq.heappop(h)[1])
    else:
      print(0)