import sys
# sys.stdin = open('/Users/kimtaeyeong/CodingTest/BaekJoon/2667/2667.txt', 'r')
from collections import deque

n = int(sys.stdin.readline().strip())
board = [list(map(int, sys.stdin.readline().strip())) for _ in range(n)]
visited = [[False for _ in range(n)] for _ in range(n)]

ans = []
def bfs(r, c):
  visited[r][c] = True
  cnt = 1
  q = deque([[r, c]])
  while(len(q) != 0):
    pos = q.popleft()
    for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
      nr = pos[0] + dr
      nc = pos[1] + dc
      if(0 <= nr < n and 0 <= nc < n):
        if(board[nr][nc] == 1 and not visited[nr][nc]):
          visited[nr][nc] = True
          q.append([nr, nc])
          cnt += 1
  ans.append(cnt)

for row in range(n):
  for col in range(n):
    if(board[row][col] == 1 and not visited[row][col]):
      bfs(row, col)

ans.sort()
print(len(ans))
for elem in ans:
  print(elem)