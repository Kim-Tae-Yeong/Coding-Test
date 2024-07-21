import sys
tmp = []

p, m = map(int, input().split())

for _ in range(p):
  info = list(sys.stdin.readline().strip().split())
  level = int(info[0])
  nickname = info[1]

  length = len(tmp)
  if(length == 0):
    tmp.append("Waiting!")
    tmp.append([1, [level, nickname]])
    if(tmp[1][0] == m):
      tmp[0] = "Started!"
  else:
    check = 0
    for i in range(1, length, 2):
      if(tmp[i][0] < m):
        if(-10 <= tmp[i][1][0] - level <= 10):
          tmp[i].append([level, nickname])
          tmp[i][0] += 1
          if(tmp[i][0] == m):
            tmp[i - 1] = "Started!"
          check = 1
          break
    
    if(check == 0):
      tmp.append("Waiting!")
      tmp.append([1, [level, nickname]])
      if(tmp[-1][0] == m):
        tmp[-2] = "Started!"

ans = []
for idx, elem in enumerate(tmp):
  if(idx % 2 == 0):
    ans.append(elem)
  else:
    candidate = elem[1 : ]
    candidate.sort(key = lambda x : x[1])
    ans.append(candidate)

for idx, elem in enumerate(ans):
  if(idx % 2 == 0):
    print(elem)
  else:
    for person in elem:
      print(person[0], person[1])