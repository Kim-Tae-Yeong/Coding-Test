t = int(input())
ans = []

for _ in range(t):
  n = int(input())
  rank = list(map(int, input().split()))

  dic = {}
  for elem in rank:
    if(elem in dic):
      dic[elem][0] += 1
    else:
      dic[elem] = [1, 0, 0, 0]

  score = 0
  for idx, elem in enumerate(rank):
    if(dic[elem][0] == 6):
      score += 1
      if(dic[elem][2] < 4):
        dic[elem][1] += score
        dic[elem][2] += 1
      elif(dic[elem][2] == 4):
        dic[elem][3] = idx + 1
        dic[elem][2] += 1

  winner_score = 9999999999
  candidate = []
  for key, value in dic.items():
    if(value[1] != 0):
      if(value[1] < winner_score):
        winner_score = value[1]
        candidate = []
        candidate.append(key)
      elif(value[1] == winner_score):
        candidate.append(key)

  if(len(candidate) == 1):
    ans.append(candidate[0])
  else:
    player_5 = 1000
    for elem in candidate:
      if(player_5 > dic[elem][3]):
        player_5 = dic[elem][3]
        winner = elem
    ans.append(winner)

for elem in ans:
  print(elem)