import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/2630/2630.txt', 'rt')
from collections import deque

n = int(sys.stdin.readline().strip())
board = []
for _ in range(n):
  tmp = list(map(int, sys.stdin.readline().strip().split()))
  board.append(tmp)

ans = [0, 0]
q = deque([[0, 0, n]])
while(len(q) != 0):
  pos = q.popleft()
  row = pos[0]
  col = pos[1]
  length = pos[2]
  check = 0
  for i in range(row, row + length):
    for j in range(col, col + length):
      if(i == row and j == col):
        start = board[i][j]
      else:
        if(start != board[i][j]):
          q.append([row, col, length // 2])
          q.append([row, col + (length // 2), length // 2])
          q.append([row + (length // 2), col, length // 2])
          q.append([row + (length // 2), col + (length // 2), length // 2])
          check = 1
          break
    if(check == 1):
      break
  if(check == 0):
    ans[start] += 1

print(ans[0])
print(ans[1])