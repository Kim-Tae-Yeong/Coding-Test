n = int(input())
info = list(map(int, input().split()))
ans = [0 for _ in range(n)]
ans[info[0]] = 1

for i in range(1, n):
  cnt = 0
  for j in range(n):
    if(ans[j] != 0):
      continue
    if(cnt == info[i]):
      ans[j] = i + 1
      break
    if(ans[j] == 0):
      cnt += 1

for elem in ans:
  print(elem, end = " ")