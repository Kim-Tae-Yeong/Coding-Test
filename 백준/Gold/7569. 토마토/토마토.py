import sys
# sys.stdin = open('/Users/kimtaeyeong/CodingTest/BaekJoon/7569/7569.txt', 'r')
from collections import deque

m, n, h = map(int, sys.stdin.readline().strip().split())
cnt = 0
board = [[[0 for _ in range(m)] for _ in range(n)] for _ in range(h)]
visited = [[[False for _ in range(m)] for _ in range(n)] for _ in range(h)]
q = deque([])
for i in range(h):
  for j in range(n):
    tmp = list(map(int, sys.stdin.readline().strip().split()))
    for k in range(m):
      if(tmp[k] == 0):
        cnt += 1
      elif(tmp[k] == 1):
        q.append([i, j, k, 0])
    board[i][j] = tmp

def bfs(queue, cnt):
  while(len(queue) != 0):
    pos = queue.popleft()
    z = pos[0]
    r = pos[1]
    c = pos[2]
    d = pos[3]
    visited[z][r][c] = True
    for dz, dr, dc in [(1, 0, 0), (-1, 0, 0), (0, 1, 0), (0, -1, 0), (0, 0, 1), (0, 0, -1)]:
      nz = z + dz
      nr = r + dr
      nc = c + dc
      if(0 <= nz < h and 0 <= nr < n and 0 <= nc < m):
        if(board[nz][nr][nc] == 0 and not visited[nz][nr][nc]):
          visited[nz][nr][nc] = True
          cnt -= 1
          queue.append([nz, nr, nc, d + 1])
  if(cnt == 0):
    return d
  else:
    return -1

if(cnt == 0):
  print(0)
else:
  print(bfs(q, cnt))