import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/21736/21736.txt', 'rt')
from collections import deque

n, m = map(int, sys.stdin.readline().strip().split())
board = [list(map(str, sys.stdin.readline().strip())) for _ in range(n)]

visited = [[False for _ in range(m)] for _ in range(n)]

def bfs(r, c):
  cnt = 0
  visited[r][c] = True
  q = deque([])
  q.append([r, c])
  dir = [(1, 0), (0, 1), (-1, 0), (0, -1)]
  while(len(q) != 0):
    pos = q.popleft()
    row = pos[0]
    col = pos[1]
    if(board[row][col] == 'P'):
      cnt += 1
    for dr, dc in dir:
      nr = row + dr
      nc = col + dc
      if(0 <= nr < n and 0 <= nc < m):
        if(board[nr][nc] != 'X' and visited[nr][nc] == False):
          visited[nr][nc] = True
          q.append([nr, nc])
  return cnt



for i in range(n):
  for j in range(m):
    if(board[i][j] == 'I'):
      ans = bfs(i, j)

if(ans == 0):
  print('TT')
else:
  print(ans)