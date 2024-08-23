import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/1012/1012.txt', 'rt')
sys.setrecursionlimit(10 ** 6)

t = int(sys.stdin.readline().strip())
ans = []
for _ in range(t):
  m, n, k = map(int, sys.stdin.readline().strip().split())
  board = [[0 for _ in range(n)] for _ in range(m)]
  for _ in range(k):
    row, col = map(int, sys.stdin.readline().strip().split())
    board[row][col] = 1
      
  visited = [[False for _ in range(n)] for _ in range(m)]
  def dfs(r, c):
    visited[r][c] = True
    for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
      nr = r + dr
      nc = c + dc
      if(0 <= nr < m and 0 <= nc < n):
        if(board[nr][nc] == 1 and visited[nr][nc] == False):
          dfs(nr, nc)

  cnt = 0
  for r in range(m):
    for c in range(n):
      if(board[r][c] == 1 and visited[r][c] == False):
        cnt += 1
        dfs(r, c)

  ans.append(str(cnt))

print('\n'.join(ans))