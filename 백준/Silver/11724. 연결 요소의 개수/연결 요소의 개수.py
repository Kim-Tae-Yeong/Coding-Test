import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/11724/11724.txt', 'rt')
from collections import deque

n, m = map(int, sys.stdin.readline().strip().split())
graph = [[] for _ in range(n + 1)]
for _ in range(m):
  u, v = map(int, sys.stdin.readline().strip().split())
  graph[u].append(v)
  graph[v].append(u)

visited = [False for _ in range(n + 1)]
def bfs(start):
  q = deque([])
  q.append(start)
  visited[start] = True
  while(len(q) != 0):
    node = q.popleft()
    for elem in graph[node]:
      if(not visited[elem]):
        visited[elem] = True
        q.append(elem)

ans = 0
for i in range(1, n + 1):
  if(not visited[i]):
    ans += 1
    bfs(i)

print(ans)