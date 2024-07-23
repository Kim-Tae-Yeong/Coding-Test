import sys

n = int(input())
storage = [0 for _ in range(10000)]
first_x = 10000
last_x = 0
max_height = 0
idx = 0

for _ in range(n):
  x, y = map(int, sys.stdin.readline().strip().split())
  if(x <= first_x):
    first_x = x
  if(x >= last_x):
    last_x = x
  if(y >= max_height):
    max_height = y
    idx = x
  storage[x] = y

board = storage[ : last_x + 1]
tmp = board[first_x]
ans = 0
for i in range(idx + 1):
  if(i < first_x):
    continue
  if(board[i] != 0):
    if(board[i] >= tmp):
      tmp = board[i]
  ans += tmp

if(idx != last_x):
  max_y = [0 for _ in range(last_x + 1)]
  max_y[-1] = board[-1]

  for i in range(last_x - 1, idx, -1):
    max_y[i] = max(board[i], max_y[i + 1])

  for elem in max_y:
    ans += elem

print(ans)