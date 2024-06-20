N = int(input())

if(N == 1):
  ans = 1
else:
  N -= 1
  ans = 1
  while True:
    N -= 6 * ans
    ans += 1
    if(N <= 0):
      break

print(ans)