import sys
from collections import deque
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/2606/2606.txt', 'rt')

n = int(sys.stdin.readline().strip())
m = int(sys.stdin.readline().strip())
info = [[] for _ in range(n + 1)]
for _ in range(m):
  x, y = map(int, sys.stdin.readline().strip().split())
  info[x].append(y)
  info[y].append(x)

visited = [False for _ in range(n + 1)]
def bfs(start):
  ans = 0
  visited[start] = True
  q = deque()
  q.append(start)
  while q:
    num = q.popleft()
    for elem in info[num]:
      if(not visited[elem]):
        visited[elem] = True
        q.append(elem)
        ans += 1
  return ans

print(bfs(1))