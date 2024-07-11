import sys

n, g = input().split()
game = {'Y' : 1, 'F' : 2, 'O' : 3}
name = set()
check = game[g]
cnt = 0
ans = 0

for _ in range(int(n)):
  player = sys.stdin.readline().strip()
  if player not in name:
    name.add(player)
    cnt += 1
    if(cnt == check):
      ans += 1
      cnt = 0

print(ans)