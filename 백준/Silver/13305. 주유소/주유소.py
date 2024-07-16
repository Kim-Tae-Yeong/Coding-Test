n = int(input())
length = list(map(int, input().split()))
cost = list(map(int, input().split()))
min_cost = cost[0]
ans = min_cost * length[0]

for i in range(1, n - 1):
  if(cost[i] < min_cost):
    min_cost = cost[i]
  ans += min_cost * length[i]

print(ans)