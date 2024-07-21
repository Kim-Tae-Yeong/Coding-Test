import sys

n, m = map(int, input().split())
memo = set()
ans = []

for _ in range(n):
  keyword = sys.stdin.readline().strip()
  memo.add(keyword)

for _ in range(m):
  write = list(map(str, sys.stdin.readline().strip().split(',')))
  for elem in write:
    memo.discard(elem)
  ans.append(str(len(memo)))

print("\n".join(ans))