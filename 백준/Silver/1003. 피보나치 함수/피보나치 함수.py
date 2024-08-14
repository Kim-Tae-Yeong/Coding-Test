t = int(input())
ans = []
for _ in range(t):
  n = int(input())
  if(n == 0):
    cnt = [[1, 0]]
  else:
    cnt = [[1, 0], [0, 1]]
    for _ in range(2, n + 1):
      cnt.append([(cnt[-1][0] + cnt[-2][0]), (cnt[-1][1] + cnt[-2][1])])
  ans.append(cnt[-1])

for elem in ans:
  print(elem[0], elem[1])