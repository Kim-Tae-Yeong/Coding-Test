n = int(input())
time = list(map(int, input().split()))

time.sort()
pre_time = 0
ans = 0
for elem in time:
  ans += (elem + pre_time)
  pre_time += elem

print(ans)