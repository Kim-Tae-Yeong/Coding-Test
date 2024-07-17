def dfs(r, c, d, cost):
  if(r == n - 1):
    candidate.append(cost)
    return 0
  
  for nd in range(3):
    nr = r + 1
    nc = c + (nd - 1)
    if(0 <= nc < m and d != nd):
      new_cost = cost + board[nr][nc]
      dfs(nr, nc, nd, new_cost)

n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

candidate = []

for dcol in range(m):
  dfs(0, dcol, -1, board[0][dcol])

print(min(candidate))