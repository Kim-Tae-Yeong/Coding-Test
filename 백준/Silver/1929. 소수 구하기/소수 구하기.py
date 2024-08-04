m, n = map(int, input().split())

num = [1 for _ in range(n + 1)]
num[1] = 0
for i in range(2, n + 1):
  idx = i
  while True:
    idx += i
    if(idx > n):
      break
    num[idx] = 0

for i in range(m, n + 1):
  if(num[i] == 1):
    print(i)