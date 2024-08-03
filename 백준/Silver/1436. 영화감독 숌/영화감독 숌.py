n = int(input())
start = 666
ans = 0
while True:
  num = start
  cnt = 0
  check = 0
  while True:
    if(check >= 3):
      ans += 1
      break
    if(num == 0):
      break
    tmp = num % 10
    if(tmp == 6):
      check += 1
    else:
      check = 0
    num //= 10
  if(ans == n):
    break
  start += 1

print(start)