import sys
# sys.stdin = open('/Users/kimtaeyeong/CodingTest/BaekJoon/7576/7576.txt', 'r')
from collections import deque

m, n = map(int, sys.stdin.readline().strip().split())
board = [[0 for _ in range(m)] for _ in range(n)]
visited = [[False for _ in range(m)] for _ in range(n)]
cnt = 0
queue = deque([])
for i in range(n):
  tmp = list(map(int, sys.stdin.readline().strip().split()))
  for j in range(m):
    if(tmp[j] == 0):
      cnt += 1
    elif(tmp[j] == 1):
      queue.append([i, j, 0])
      visited[i][j] = True
  board[i] = tmp

def bfs(q, c):
  while(len(q) != 0):
    pos = q.popleft()
    for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
      nr = pos[0] + dr
      nc = pos[1] + dc
      if(0 <= nr < n and 0 <= nc < m):
        if(board[nr][nc] == 0 and not visited[nr][nc]):
          visited[nr][nc] = True
          q.append([nr, nc, pos[2] + 1])
          c -= 1
  if(c == 0):
    return pos[2]
  else:
    return -1
  
if(cnt == 0):
  print(0)
else:
  print(bfs(queue, cnt))