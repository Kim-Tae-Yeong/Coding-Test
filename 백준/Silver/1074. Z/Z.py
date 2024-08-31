import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/1074/1074.txt', 'rt')

n, r, c = map(int, sys.stdin.readline().strip().split())
lenght = (2 ** n) // 2
ans = 0
while True:
  if(0 <= r <= 1 and 0 <= c <= 1):
    break
  row = r // lenght
  ans += lenght * (lenght * 2) * row
  col = c // lenght
  ans += lenght * lenght * col
  r = r - (row * lenght)
  c = c - (col * lenght)
  lenght = lenght // 2

if(r == 0 and c == 0):
  print(ans)
elif(r == 0 and c == 1):
  print(ans + 1)
elif(r == 1 and c == 0):
  print(ans + 2)
else:
  print(ans + 3)