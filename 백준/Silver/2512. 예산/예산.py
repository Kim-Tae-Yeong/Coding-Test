n = int(input())
cost = list(map(int, input().split()))
m = int(input())

cost.sort()
min_cost = 0
max_cost = 0
ans = 0

for i in range(n):
  if(cost[i] * n >= m):
    if(i == 0):
      ans = m // n
    else:
      max_cost = cost[i]
      min_cost = cost[i - 1]
    break
  else:
    m -= cost[i]
    n -= 1

if(n == 0):
  ans = cost[-1]
else:
  for i in range(min_cost, max_cost + 1):
    if(i * n > m):
      ans = i - 1
      break
    elif(i * n == m):
      ans = i
      break

print(ans)