n = int(input())
body = []

for _ in range(n):
  tmp = input()
  body.append(tmp)

check = 0
for i in range(n):
  for j in range(n):
    if(body[i][j] == '*'):
      row_head = i
      col_head = j
      check = 1
      break
  if(check == 1):
    break

row_heart = row_head + 1
col_heart = col_head

left_hand = 0
for dcol in range(col_heart - 1, -1, -1):
  if(body[row_heart][dcol] == '*'):
    left_hand += 1
  else:
    break

right_hand = 0
for dcol in range(col_heart + 1, n):
  if(body[row_heart][dcol] == '*'):
    right_hand += 1
  else:
    break

back = 0
for drow in range(row_heart + 1, n):
  if(body[drow][col_heart] == '*'):
    back += 1
  else:
    break

left_leg = 0
for drow in range(row_heart + back + 1, n):
  if(body[drow][col_heart - 1] == '*'):
    left_leg += 1
  else:
    break

right_leg = 0
for drow in range(row_heart + back + 1, n):
  if(body[drow][col_heart + 1] == '*'):
    right_leg += 1
  else:
    break

print(row_heart + 1, col_heart + 1)
print(left_hand, right_hand, back, left_leg, right_leg)