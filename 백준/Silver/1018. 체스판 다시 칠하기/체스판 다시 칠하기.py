n, m = map(int, input().split())
board = []
cnt = [[] for _ in range(n)]
color = ['B', 'W']
ans = []
for i in range(n):
  tmp = list(input())
  board.append(tmp)
  for j in range(m - 8 + 1):
    num = [0, 0]
    for k in range(j, j + 8):
      if(tmp[k] != color[k % 2]):
        num[0] += 1
      elif(tmp[k] != color[(k + 1) % 2]):
        num[1] += 1
    cnt[i].append(num)

for col in range(m - 8 + 1):
  for row in range(n - 8 + 1):
    ans1 = 0
    ans2 = 0
    for i in range(row, row + 8):
      ans1 += cnt[i][col][i % 2]
      ans2 += cnt[i][col][(i + 1) % 2]
    ans.append(min(ans1, ans2))

print(min(ans))