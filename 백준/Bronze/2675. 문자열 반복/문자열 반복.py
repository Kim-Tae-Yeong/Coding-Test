t = int(input())

ans = []
for _ in range(t):
  r, s = map(str, input().split())
  length = len(s)
  tmp = ''
  for i in range(length):
    for _ in range(int(r)):
      tmp += s[i]
  ans.append(tmp)

print('\n'.join(ans))