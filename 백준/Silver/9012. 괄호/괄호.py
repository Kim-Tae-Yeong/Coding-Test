import sys

t = int(input())
ans = []
for _ in range(t):
  tmp = sys.stdin.readline().strip()
  s = []
  check = 0
  for elem in tmp:
    if(elem == '('):
      s.append(elem)
    else:
      if(len(s) == 0):
        ans.append('NO')
        check = 1
        break
      s.pop()
  if(check == 0):
    if(len(s) != 0):
      ans.append('NO')
    else:
      ans.append('YES')

print('\n'.join(ans))