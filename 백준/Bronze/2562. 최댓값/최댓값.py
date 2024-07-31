ans = 0
idx = 0
for i in range(1, 10):
  num = int(input())
  if(num >= ans):
    ans = num
    idx = i

print(ans)
print(idx)