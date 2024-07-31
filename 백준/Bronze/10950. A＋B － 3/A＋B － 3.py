t = int(input())

ans = []
for _ in range(t):
  a, b = map(int, input().split())
  ans.append(str(a + b))

print('\n'.join(ans))