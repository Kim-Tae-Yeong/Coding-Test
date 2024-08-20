import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/1446/1446.txt', 'rt')

n, d = map(int, sys.stdin.readline().strip().split())
info = []
for _ in range(n):
  start, end, short = map(int, sys.stdin.readline().strip().split())
  if(start > d or end > d):
    continue
  if(short < end - start):
    info.append([start, end, short])

dis = [i for i in range(d + 1)]
idx = 0
for i in range(d + 1):
  if(i != 0):
    dis[i] = min(dis[i - 1] + 1, dis[i])
  for start, end, short in info:
    if(i == start and dis[start] + short < dis[end]):
      dis[end] = dis[start] + short
    
print(dis[-1])