import sys
from collections import deque

n, m, v = map(int, input().split())

graph = [[] for _ in range(n + 1)]
for _ in range(m):
  n1, n2 = map(int, input().split())
  graph[n1].append(n2)
  graph[n2].append(n1)

for elem in graph:
  elem.sort()

dfs_visited = [False for _ in range(n + 1)]
dfs_ans = []
def dfs(start):
  dfs_visited[start] = True
  dfs_ans.append(str(start))
  for elem in graph[start]:
    if not dfs_visited[elem]:
      dfs(elem)

bfs_visited = [False for _ in range(n + 1)]
bfs_ans = []
bfs_list = [v]
def bfs(start):
  if not bfs_visited[start]:
    bfs_visited[start] = True
    bfs_ans.append(str(start))
  for elem in graph[start]:
    if not bfs_visited[elem]:
      bfs_visited[elem] = True
      bfs_ans.append(str(elem))
      bfs_list.append(elem)

dfs(v)
print(" ".join(dfs_ans))

for elem in bfs_list:
  bfs(elem)
print(" ".join(bfs_ans))