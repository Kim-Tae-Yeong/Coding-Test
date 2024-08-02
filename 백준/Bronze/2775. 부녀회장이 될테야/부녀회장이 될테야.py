t = int(input())
ans = []
for _ in range(t):
  k = int(input())
  n = int(input())
  tmp = [[] for _ in range(k + 1)]
  for i in range(n):
    tmp[0].append(i + 1)
  for i in range(1, k + 1):
    for j in range(n):
      tmp[i].append(sum(tmp[i - 1][: j + 1]))
  ans.append(str(tmp[k][n - 1]))

print('\n'.join(ans))