from collections import deque

n, m = map(int, input().split())
board = []
ans = [[False for _ in range(m)] for _ in range(n)]
start_row = -1
start_col = -1
for i in range(n):
  row = list(map(int, input().split()))
  if(start_row == -1):
    for j in range(m):
      if(row[j] == 2):
        start_row = i
        start_col = j
  board.append(row)

ans[start_row][start_col] = 0

def check(r, c):
  if(r >= 0 and r <= n - 1 and c >= 0 and c <= m - 1):
    return True
  return False

def bfs(r, c, d):
  if(board[r][c] == 1):
    if(ans[r][c] == False):
      ans[r][c] = d + 1
      tmp.append([r, c])
    else:
      if(ans[r][c] > d + 1):
        ans[r][c] = d + 1
        tmp.append([r, c])
        
tmp = deque([[start_row, start_col]])

while(len(tmp) != 0):
  pos = tmp.popleft()
  r = pos[0]
  c = pos[1]
  d = ans[r][c]
  if(check(r - 1, c)):
    bfs(r - 1, c, d)
  if(check(r, c - 1)):
    bfs(r, c - 1, d)
  if(check(r + 1, c)):
    bfs(r + 1, c, d)
  if(check(r, c + 1)):
    bfs(r, c + 1, d)

for i in range(n):
  for j in range(m):
    if(ans[i][j] == False):
      if(board[i][j] == 0):
        ans[i][j] = 0
      elif(board[i][j] == 1):
        ans[i][j] = -1

for i in range(n):
  for j in range(m):
    print(ans[i][j], end = ' ')
  print()