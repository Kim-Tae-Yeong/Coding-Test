import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/1541/1541.txt', 'rt')

board = list(sys.stdin.readline().strip())
check = True
num = ''
ans = []
s = []
for elem in board:
  if(elem == '+'):
    if(check):
      ans.append(int(num))
    else:
      s.append(int(num))
    num = ''
  elif(elem == '-'):
    if(check):
      ans.append(int(num))
      num = ''
      check = False
    else:
      s.append(int(num))
      num = ''
  else:
    num += elem
if(check):
  ans.append(int(num))
else:
  s.append(int(num))

print(sum(ans) - sum(s))