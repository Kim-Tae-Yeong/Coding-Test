n = int(input())
m = int(input())
x = list(map(int, input().split()))

max_gap = max(x[0], n - x[m - 1])
check = 0
for i in range(m - 1):
  if((x[i + 1] - x[i]) % 2 == 0):
    if(((x[i + 1] - x[i]) // 2) > max_gap):
      max_gap = (x[i + 1] - x[i]) // 2
  else:
    if((((x[i + 1] - x[i]) // 2) + 1) > max_gap):
      max_gap = ((x[i + 1] - x[i]) // 2) + 1

print(max_gap)