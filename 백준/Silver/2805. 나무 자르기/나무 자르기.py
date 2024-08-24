import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/2805/2805.txt', 'rt')

n, m = map(int, sys.stdin.readline().strip().split())
height = list(map(int, sys.stdin.readline().strip().split()))

start = 0
end = max(height)
while(start <= end):
  mid = (start + end) // 2
  tmp = 0
  for elem in height:
    if(elem - mid > 0):
      tmp += (elem - mid)
  if(tmp >= m):
    start = mid + 1
  else:
    end = mid - 1

print(end)