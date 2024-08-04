k = int(input())
ans = []
for _ in range(k):
  num = int(input())
  if(num == 0):
    ans.pop()
  else:
    ans.append(num)

print(sum(ans))