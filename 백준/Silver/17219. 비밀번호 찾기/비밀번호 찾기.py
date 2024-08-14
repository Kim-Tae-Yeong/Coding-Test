import sys

n, m = map(int, input().split())
info = {}
for _ in range(n):
  site, pw = map(str, sys.stdin.readline().strip().split())
  info[site] = pw

ans = []
for _ in range(m):
  tmp = str(sys.stdin.readline().strip())
  ans.append(info[tmp])

print('\n'.join(ans))