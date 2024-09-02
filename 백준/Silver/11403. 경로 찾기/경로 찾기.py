import sys
# sys.stdin = open('/Users/kimtaeyeong/CodingTest/BaekJoon/11403/11403.txt', 'r')
from collections import deque

n = int(sys.stdin.readline().strip())
graph = [[]]

for i in range(n):
  tmp = list(map(int, sys.stdin.readline().strip().split()))
  board = []
  for idx, elem in enumerate(tmp):
    if(elem == 1):
      board.append(idx + 1)
  graph.append(board)

ans = [[0 for _ in range(n + 1)] for _ in range(n + 1)]
def bfs(s):
  q = deque([s])
  visited = [False] * (n + 1)
  while(len(q) != 0):
    node = q.popleft()
    for elem in graph[node]:
      if(not visited[elem]):
        visited[elem] = True
        q.append(elem)
        ans[s][elem] = 1

for i in range(1, n + 1):
  bfs(i)

for i in range(1, n + 1):
  for idx, elem in enumerate(ans[i]):
    if(idx == 0):
      continue
    print(elem, end = ' ')
  print()