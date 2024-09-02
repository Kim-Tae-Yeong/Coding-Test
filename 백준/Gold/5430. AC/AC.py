import sys
# sys.stdin = open('/Users/kimtaeyeong/CodingTest/BaekJoon/5430/5430.txt', 'r')
from collections import deque

t = int(sys.stdin.readline().strip())
for _ in range(t):
  p = list(sys.stdin.readline().strip())
  n = int(sys.stdin.readline().strip())
  board = sys.stdin.readline().strip()[1 : -1].split(',')
  board = deque(board)

  reverse = False
  check = 0
  for elem in p:
    if(elem == 'R'):
      reverse = not reverse
    elif(elem == 'D'):
      if(len(board) == 0 or board[0] == ''):
        check = 1
        break
      else:
        if(reverse):
          board.pop()
        else:
          board.popleft()

  if(check == 1):
    print('error')
  else:
    print('[', end = '')
    length = len(board)
    if(reverse):
      for i in range(length - 1, -1, -1):
        print(board[i], end = '')
        if(i != 0):
          print(',', end = '')
      print(']')
    else:
      for i in range(length):
        print(board[i], end = '')
        if(i != (length - 1)):
          print(',', end = '')
      print(']')
