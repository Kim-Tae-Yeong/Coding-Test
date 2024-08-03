n, k = map(int, input().split())
tmp = [1 for _ in range(n)]
idx = 0
ans = []
for _ in range(n):
  cnt = 0
  while True:
    if(tmp[idx % n] == 1):
      cnt += 1
    if(cnt == k):
      if((idx + 1) % n == 0):
        ans.append(n)
      else:
        ans.append((idx + 1) % n)
      tmp[idx % n] = 0
      cnt = 0
      break
    idx += 1

print('<', end = '')
for idx, elem in enumerate(ans):
  if(idx != n - 1):
    print(elem, end = ', ')
  else:
    print(elem, end = '')
print('>')