n = int(input())

s = []
ans = []
cnt = 1
possible = True
for _ in range(n):
  num = int(input())
  if(len(s) == 0):
    for i in range(cnt, num + 1):
      s.append(i)
      ans.append('+')
    cnt = num + 1
  elif(num >= cnt):
    for i in range(cnt, num + 1):
      s.append(i)
      ans.append('+')
    cnt = num + 1
  if(s[-1] == num):
    s.pop()
    ans.append('-')
  else:
    possible = False
    break

if(not possible):
  print('NO')
else:
  print('\n'.join(ans))