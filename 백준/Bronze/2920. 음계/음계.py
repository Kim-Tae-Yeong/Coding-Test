num = list(map(int, input().split()))

check = 0
if(num[0] == 1):
  for i in range(1, 8):
    if(num[i] != (i + 1)):
      check = 1
      print('mixed')
      break
  if(check == 0):
    print('ascending')
elif(num[0] == 8):
  for i in range(1, 8):
    if(num[i] != (8 - i)):
      check = 1
      print('mixed')
      break
  if(check == 0):
    print('descending')
else:
  print('mixed')