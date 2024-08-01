n, m = map(int, input().split())
card = list(map(int, input().split()))

ans = 0
for i in range(n):
  for j in range(i + 1, n):
    for k in range(j + 1, n):
      if(card[i] + card[j] + card[k] <= m):
        ans = max(ans, card[i] + card[j] + card[k])

print(ans)