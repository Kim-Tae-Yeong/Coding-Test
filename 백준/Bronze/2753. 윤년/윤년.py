year = int(input())

if(year % 4 == 0):
  if(year % 100 != 0 or year % 400 == 0):
    ans = 1
  else:
    ans = 0
else:
  ans = 0

print(ans)