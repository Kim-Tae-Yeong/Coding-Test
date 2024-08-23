import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/17626/17626.txt', 'rt')

n = int(sys.stdin.readline().strip())
if(n <= 3):
  print(n)
else:
  ans = [4 for _ in range(n + 1)]
  ans[1] = 1
  ans[2] = 2
  ans[3] = 3
  num = []
  cnt = 2
  num = [1]
  for i in range(4, n + 1):
    if(i == (cnt ** 2)):
      ans[i] = 1
      cnt += 1
      num.append(i)
  for i in range(4, n + 1):
    if(ans[i] == 1):
      for elem in num:
        if(i + elem <= n and ans[i + elem] == 4):
          ans[i + elem] = 2
  for i in range(4, n + 1):
    if(ans[i] == 2):
      for elem in num:
        if(i + elem <= n and ans[i + elem] == 4):
          ans[i + elem] = 3
  print(ans[-1])