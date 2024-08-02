ans = []

while True:
  num = input()
  if(num == '0'):
    break
  length = len(num)
  check = 0
  for i in range((length // 2) + 1):
    if(num[i] != num[length - 1 - i]):
      check = 1
      break
  if(check == 0):
    ans.append('yes')
  else:
    ans.append('no')

print('\n'.join(ans))