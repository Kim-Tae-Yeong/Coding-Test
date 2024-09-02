import sys
# sys.stdin = open('/Users/kimtaeyeong/CodingTest/BaekJoon/6064/6064.txt', 'r')

t = int(sys.stdin.readline().strip())
ans = []
for _ in range(t):
  m, n, x, y = map(int, sys.stdin.readline().strip().split())

  while True:
    if(x > m * n):
      ans.append(str(-1))
      break
    if((x - 1) % n + 1 == y):
      ans.append(str(x))
      break
    x += m

print('\n'.join(ans))