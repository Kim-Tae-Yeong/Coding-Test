n = int(input())

tmp = [0 for _ in range(n + 1)]

for i in range(1, n + 1):
  if(i == 1 or i == 3):
    tmp[i] = 'SK'
  elif(i == 2):
    tmp[i] = 'CY'
  else:
    tmp1 = tmp[i - 1]
    tmp2 = tmp[i - 3]
    if((tmp1 or tmp2) == 'CY'):
      tmp[i] = 'SK'
      continue
    else:
      tmp[i] = 'CY'

print(tmp[n])