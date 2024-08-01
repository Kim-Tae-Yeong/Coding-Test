n = int(input())

for i in range(1, n + 1):
  num = i
  ans = i
  while True:
    num += i % 10
    i = i // 10
    if(i == 0):
      break
  if(num == n):
    break

if(ans == n):
  print(0)
else:
  print(ans)