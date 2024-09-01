import sys
# sys.stdin = open('/Users/kimtaeyeong/CodingTest/BaekJoon/5525/5525.txt', 'r')

n = int(sys.stdin.readline().strip())
m = int(sys.stdin.readline().strip())
s = list(map(str, sys.stdin.readline().strip()))
length = 2 * n + 1

board = []
for i in range(length):
  if((i % 2) == 0):
    board.append('I')
  else:
    board.append('O')

ans = 0
for i in range(m - length + 1):
  if(s[i] == 'I'):
    if(s[i : i + length] == board):
      ans += 1

print(ans)