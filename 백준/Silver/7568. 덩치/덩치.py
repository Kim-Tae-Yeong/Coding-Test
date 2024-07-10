n = int(input())
tmp = []
ans = []

for _ in range(n):
  weight, heigth = map(int, input().split())
  tmp.append([weight, heigth])

for i in range(n):
  x = tmp[i][0]
  y = tmp[i][1]
  cnt = 1
  for j in range(n):
    if(x < tmp[j][0] and y < tmp[j][1]):
      cnt += 1
  ans.append(cnt)

for elem in ans:
  print(elem, end = " ")