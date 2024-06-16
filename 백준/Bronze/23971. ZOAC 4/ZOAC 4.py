H, W, N, M = map(int, input().split())
x = 1
y = 1
line = 0
ans = 0

while(x <= W):
  line += 1
  x += (M +1)

while(y <= H):
  ans += line
  y += (N + 1)

print(ans)