num = list(map(int, input().split()))

ans = 0
for elem in num:
  ans += elem * elem

print(ans % 10)