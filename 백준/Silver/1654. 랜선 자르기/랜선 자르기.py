import sys

k, n = map(int, input().split())

tmp = []
for _ in range(k):
  num = int(sys.stdin.readline().strip())
  tmp.append(num)

start_length = 1
end_length = max(tmp)
ans = []
while(start_length <= end_length):
  mid_length = (start_length + end_length) // 2
  cnt = 0
  for elem in tmp:
    cnt += (elem // mid_length)
  if(cnt >= n):
    start_length = mid_length + 1
  else:
    end_length = mid_length - 1

print(end_length)