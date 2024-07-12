n, score, p = map(int, input().split())

if(n == 0):
  print(1)
else:
  board = list(map(int, input().split()))

  if(board[-1] >= score):
    board.append(score)
  else:
    board.append(0)
    length = len(board)
    idx = 0
    for i, elem in enumerate(board):
      if(elem < score):
        idx = i
        break
    for i in range(length - 2, idx - 1, -1):
      board[i + 1] = board[i]
    board[idx] = score

  check = 0
  cnt = 1
  rank = 1
  ans = -1
  for i, elem in enumerate(board):
    if(check <= p):
      if(elem > score):
        rank += cnt
        cnt = 1
      elif(elem == score):
        cnt += 1
      else:
        ans = rank
      check += 1
    else:
      break
    
  if(check <= p):
    print(rank)
  else:
    print(ans)