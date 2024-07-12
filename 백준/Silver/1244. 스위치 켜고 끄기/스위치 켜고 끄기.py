n = int(input())
switch = list(map(int, input().split()))
k = int(input())
for _ in range(k):
  s, num = map(int, input().split())
  if(s == 1):
    for i in range(num, n + 1, num):
      if(switch[i - 1] == 0):
        switch[i - 1] = 1
      else:
        switch[i - 1] = 0
  elif(s == 2):
    if(num > (n - num)):
      cnt = n - num + 1
    else:
      cnt = num
    for i in range(cnt):
      if(switch[num + i - 1] == switch[num - i - 1]):
        if(switch[num + i - 1] == 1):
          switch[num + i - 1] = 0
          switch[num - i - 1] = 0
        else:
          switch[num + i - 1] = 1
          switch[num - i - 1] = 1
      elif(switch[num + i - 1] != switch[num - i - 1]):
        break

check = 0
for i in range(n):
  print(switch[i], end = ' ')
  check += 1
  if(i != n - 1 and check == 20):
    print()
    check = 0