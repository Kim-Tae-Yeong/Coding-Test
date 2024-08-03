import sys

n = int(input())
ans = []
for i in range(n):
  age, name = map(str, sys.stdin.readline().strip().split())
  ans.append([i, int(age), name])

ans.sort(key = lambda x : (x[1], x[0]))
for elem in ans:
  for i in range(2):
    print(elem[i + 1], end = ' ')
  print()