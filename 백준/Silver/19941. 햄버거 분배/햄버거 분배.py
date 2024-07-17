n, k = map(int, input().split())
board = input()
tmp = [0 for _ in range(n)]
ans = 0

for idx, elem in enumerate(board):
  if(elem == "P"):
    tmp[idx] = 2
  else:
    tmp[idx] = 1

for idx, elem in enumerate(tmp):
  if(elem == 2):
    check = 0
    for i in range(k, 0, -1):
      if(idx - i < 0):
        continue
      if(tmp[idx - i] == 1):
        tmp[idx - i] = 0
        ans += 1
        check = 1
        break
    if(check == 0):
      for i in range(1, k + 1):
        if(idx + i > n - 1):
          continue
        if(tmp[idx + i] == 1):
          tmp[idx + i] = 0
          ans += 1
          break

print(ans)