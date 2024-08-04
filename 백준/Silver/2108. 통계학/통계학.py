import sys

n = int(input())
total = 0
tmp = []
dic = {}

for _ in range(n):
  num = int(sys.stdin.readline().strip())
  total += num
  tmp.append(num)
  if(num not in dic):
    dic[num] = 1
  else:
    dic[num] += 1

if(total % n >= (n / 2)):
  print((total // n) + 1)
else:
  print(total // n)

tmp.sort()
print(tmp[n // 2])

ans = []
max_cnt = 0
for key, value in dic.items():
  if(value > max_cnt):
    ans = [key]
    max_cnt = value
  elif(value == max_cnt):
    ans.append(key)
ans.sort()
if(len(ans) == 1):
  print(ans[0])
else:
  print(ans[1])

print(tmp[-1] - tmp[0])