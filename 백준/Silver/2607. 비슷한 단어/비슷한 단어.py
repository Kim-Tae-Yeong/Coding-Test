n = int(input())

first = input()
first_length = len(first)
fisrt_board = [0 for _ in range(26)]
for i in range(first_length):
  fisrt_board[ord(first[i]) - 65] += 1
ans = 0

for _ in range(n - 1):
  word = input()
  length = len(word)
  board = [0 for _ in range(26)]
  for i in range(length):
    board[ord(word[i]) - 65] += 1
  tmp1 = []
  tmp2 = []
  check = 0

  for i in range(26):
    board[i] = board[i] - fisrt_board[i]
    if(board[i] >= 2 or board[i] <= -2):
      check = 1
      break
    elif(board[i] == 1):
      tmp1.append(board[i])
    elif(board[i] == -1):
      tmp2.append(board[i])

  if(check == 0):
    if(len(tmp1) <= 1 and len(tmp2) <= 1):
      ans += 1

print(ans)