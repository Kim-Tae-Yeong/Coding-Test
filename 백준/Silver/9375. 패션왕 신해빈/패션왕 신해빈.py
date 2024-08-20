import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/9375/9375.txt', 'rt')

t = int(sys.stdin.readline().strip())
ans = []
for _ in range(t):
  n = int(sys.stdin.readline().strip())
  dic = {}
  for _ in range(n):
    name, category = map(str, sys.stdin.readline().strip().split())
    if(category not in dic):
      dic[category] = 1
    else:
      dic[category] += 1
  tmp = 1
  for value in dic.values():
    tmp *= (value + 1)
  tmp -= 1
  ans.append(str(tmp))

print('\n'.join(ans))