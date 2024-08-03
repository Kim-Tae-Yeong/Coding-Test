import sys

n = int(input())
ans = []
for _ in range(n):
  x, y = map(int, sys.stdin.readline().strip().split())
  ans.append([x, y])

ans.sort(key = lambda x : (x[0], x[1]))
for elem in ans:
  print(elem[0], elem[1])