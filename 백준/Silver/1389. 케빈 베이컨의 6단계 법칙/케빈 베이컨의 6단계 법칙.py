import sys
# sys.stdin = open('/Users/kimtaeyeong/CodingTest/BaekJoon/1389/1389.txt', 'rt')
from collections import deque

n, m = map(int, sys.stdin.readline().strip().split())
graph = [[] for _ in range(n + 1)]
for _ in range(m):
  x, y = map(int, sys.stdin.readline().strip().split())
  graph[x].append(y)
  graph[y].append(x)

def bfs(s):
  cnt = 0
  visited[s] = True
  q = deque()
  q.append([s, 0])
  while(len(q) != 0):
    tmp = q.popleft()
    start = tmp[0]
    num = tmp[1]
    cnt += num
    for elem in graph[start]:
      if(not visited[elem]):
        visited[elem] = True
        q.append([elem, num + 1])
  return cnt

board = [0] * (n + 1)
for i in range(1, n + 1):
  visited = [False] * (n + 1)
  board[i] = bfs(i)

bacon = board[1]
ans = 1
for i in range(2, n + 1):
  if(bacon > board[i]):
    bacon = board[i]
    ans = i

print(ans)