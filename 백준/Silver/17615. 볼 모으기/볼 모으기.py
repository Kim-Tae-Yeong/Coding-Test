n = int(input())

board = input()
tmp = []
ball = {'R' : 0, 'B' : 0}

cnt = 1
for i in range(n - 1):
  if(board[i] == board[i + 1]):
    cnt += 1
  else:
    tmp.append([board[i], cnt])
    ball[board[i]] += cnt
    cnt = 1
tmp.append([board[-1], cnt])
ball[board[-1]] += cnt

ans1 = 0
ans2 = 0
ans = 0
length = len(tmp)
if(tmp[0][0] == tmp[-1][0]):
  if(tmp[0][1] >= tmp[-1][1]):
    for i in range(1, length):
      if(tmp[i][0] == tmp[0][0]):
        ans1 += tmp[i][1]
  else:
    for i in range(length - 2, -1, -1):
      if(tmp[i][0] == tmp[-1][0]):
        ans1 += tmp[i][1]
  for i in range(1, length - 1):
    if(tmp[i][0] != tmp[0][0]):
      ans2 += tmp[i][1]
  ans = min(ans1, ans2)
else:
  if(ball[tmp[0][0]] < ball[tmp[-1][0]]):
    for i in range(1, length):
      if(tmp[i][0] == tmp[0][0]):
        ans += tmp[i][1]
  else:
    for i in range(length - 2, -1, -1):
      if(tmp[i][0] == tmp[-1][0]):
        ans += tmp[i][1]

print(ans)