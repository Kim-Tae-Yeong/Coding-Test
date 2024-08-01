t = int(input())

ans = []
for _ in range(t):
  result = input()
  length = len(result)
  score = 1
  total = 0
  for i in range(length):
    if(result[i] == 'O'):
      total += score
      score += 1
    else:
      score = 1
  ans.append(str(total))

print('\n'.join(ans))