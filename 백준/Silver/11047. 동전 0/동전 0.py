n, k = map(int, input().split())
cost = []
for _ in range(n):
  tmp = int(input())
  cost.append(tmp)

cost.sort(reverse = True)

ans = 0
for elem in cost:
  if(elem > k):
    continue
  cnt = (k // elem)
  ans += cnt
  k -= elem * cnt

print(ans)