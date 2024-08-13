n, m = map(int, input().split())

no_listen = {}
cnt = 0
ans = []

for _ in range(n):
  name = input()
  no_listen[name] = 1

for _ in range(m):
  name = input()
  if(name in no_listen):
    cnt += 1
    ans.append(name)

ans.sort()
print(cnt)
print('\n'.join(ans))