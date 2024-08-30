import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/18111/18111.txt', 'rt')

n, m, b = map(int, sys.stdin.readline().strip().split())
board = []
min_height = 257
max_heigth = -1
for _ in range(n):
  tmp = list(map(int, sys.stdin.readline().strip().split()))
  min_height = min(min_height, min(tmp))
  max_heigth = max(max_heigth, max(tmp))
  board.append(tmp)

cnt = [0] * 257
for row in range(n):
  for col in range(m):
    cnt[board[row][col]] += 1

ans_time = sys.maxsize
ans_height = -1
for height in range(min_height, max_heigth + 1):
  remove_block = 0
  add_block = 0
  for i in range(257):
    if(i >= height):
      remove_block += cnt[i] * (i - height)
    else:
      add_block += cnt[i] * (height - i)
  if(remove_block + b >= add_block):
    time = (remove_block * 2) + add_block
    if(ans_time >= time):
      ans_time = time
      ans_height = height 

print(ans_time, ans_height)