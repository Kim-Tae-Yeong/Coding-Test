import sys

t = int(input())
ans = []
for _ in range(t):
  n, m = map(int, sys.stdin.readline().strip().split())
  dic = {}
  important = list(map(int, sys.stdin.readline().strip().split()))
  for idx, elem in enumerate(important):
    dic[idx] = elem
  important.sort(reverse = True)
  num = 0
  start = 0
  tmp = 0
  while True:
    if(dic[num % n] == important[start]):
      tmp += 1
      start += 1
      if((num % n) == m):
        break
    num += 1
  ans.append(str(tmp))

print('\n'.join(ans))