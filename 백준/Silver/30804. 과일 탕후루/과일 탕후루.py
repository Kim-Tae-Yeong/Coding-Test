import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/30804/30804.txt', 'rt')

n = int(sys.stdin.readline().strip())
fruit = list(map(int, sys.stdin.readline().strip().split()))

cnt = {}
left = 0
ans = - 1
for right in range(n):
  if(fruit[right] in cnt):
    cnt[fruit[right]] += 1
  else:
    cnt[fruit[right]] = 1
  while(len(cnt) > 2):
    cnt[fruit[left]] -= 1
    if(cnt[fruit[left]] == 0):
      del cnt[fruit[left]]
    left += 1
  ans = max(ans, right - left + 1)

print(ans)