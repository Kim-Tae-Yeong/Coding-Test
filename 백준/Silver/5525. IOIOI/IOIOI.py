import sys
# sys.stdin = open('/Users/kimtaeyeong/CodingTest/BaekJoon/5525/5525.txt', 'r')

n = int(sys.stdin.readline().strip())
m = int(sys.stdin.readline().strip())
s = list(map(str, sys.stdin.readline().strip()))

start = False
board = []
for i in range(m):
  if(start):
    if(s[i - 1] != s[i]):
      if(s[i] == 'O'):
        cnt += 1
      if(i == (m - 1)):
        if(s[i] == 'I'):
          board.append(cnt)
        else:
          board.append(cnt - 1)
    else:
      if(s[i] == 'O'):
        start = False
        board.append(cnt - 1)
      else:
        board.append(cnt)
        cnt = 0
  else:
    if(s[i] == 'I'):
      start = True
      cnt = 0

ans = 0
for elem in board:
  if(elem >= n):
    ans += (elem - n + 1)

print(ans)