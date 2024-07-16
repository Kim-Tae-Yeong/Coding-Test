n, x = map(int, input().split())
visitor = list(map(int, input().split()))
max_visitor = sum(visitor[0 : x])
day = 1
tmp = max_visitor

for i in range(n - x):
  tmp = tmp - visitor[i] + visitor[i + x]
  if(tmp == max_visitor):
    day += 1
  elif(tmp > max_visitor):
    max_visitor = tmp
    day = 1

if(max_visitor == 0):
  print("SAD")
else:
  print(max_visitor)
  print(day)