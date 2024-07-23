import sys

n = int(input())
if(n == 1):
  ans = int(input())
  print(ans)
else:
  board1 = list(map(int, sys.stdin.readline().strip().split()))
  board2 = list(map(int, sys.stdin.readline().strip().split()))

  board1.sort()
  board2.sort()

  tmp = [0 for _ in range(n)]
  for i in range(n - 1, -1, -1):
    if(board1[-1] < board2[-1]):
      tmp[i] = board2.pop()
    else:
      tmp[i] = board1.pop()

  for _ in range(n - 2):
    ans = [0 for _ in range(n)]
    board = list(map(int, sys.stdin.readline().strip().split()))
    board.sort()
    for i in range(n - 1, -1, -1):
      if(board[-1] > tmp[-1]):
        ans[i] = board.pop()
      else:
        ans[i] = tmp.pop()
    tmp = ans

  print(tmp[0])