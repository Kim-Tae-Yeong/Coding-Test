import sys

t = int(input())
ans = []

for _ in range(t):
  n = int(sys.stdin.readline().strip())
  price = list(map(int, sys.stdin.readline().strip().split()))

  max_price = [0 for _ in range(n)]
  max_price[-1] = price[-1]
  for i in range(n - 2, -1, -1):
    max_price[i] = max(price[i], max_price[i + 1])

  total = 0
  for i in range(n):
    if(max_price[i] > price[i]):
      total += max_price[i] - price[i]

  ans.append(str(total))
  
print("\n".join(ans))