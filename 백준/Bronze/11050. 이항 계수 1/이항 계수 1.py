n, k = map(int, input().split())

if(k == 0):
  print(1)
else:
  ans = 1
  for i in range(n, n - k, -1):
    ans *= i
  for i in range(k, 0, -1):
    ans //= i
  print(ans)