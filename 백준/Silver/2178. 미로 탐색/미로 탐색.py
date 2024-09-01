import sys
# sys.stdin = open('/Users/kimtaeyeong/CodingTest/BaekJoon/2178/2178.txt', 'r')
from collections import deque

n, m = map(int, sys.stdin.readline().strip().split())
board = [list(map(int, sys.stdin.readline().strip())) for _ in range(n)]
visited = [[0 for _ in range(m)] for _ in range(n)]

def bfs(row, col):
  visited[row][col] = 1
  q = deque([[row, col, 1]])
  while(len(q) != 0):
    pos = q.popleft()
    r = pos[0]
    c = pos[1]
    cnt = pos[2]
    for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
      nr = r + dr
      nc = c + dc
      if(0 <= nr < n and 0 <= nc < m):
        if(board[nr][nc] == 1 and visited[nr][nc] == 0):
          visited[nr][nc] = cnt + 1
          q.append([nr, nc, cnt + 1])

bfs(0, 0)
print(visited[-1][-1])