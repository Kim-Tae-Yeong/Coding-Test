n = int(input())
num = 1
for i in range(2, n + 1):
  num *= i

ans = 0
while True:
  if(num == 0):
    break
  tmp = num % 10
  if(tmp == 0):
    ans += 1
  else:
    break
  num //= 10

print(ans)