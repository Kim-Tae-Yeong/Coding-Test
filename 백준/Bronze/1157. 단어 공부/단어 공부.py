word = input()
WORD = word.upper()
length = len(WORD)
dic = {}

for i in range(length):
  if(WORD[i] in dic):
    dic[WORD[i]] += 1
  else:
    dic[WORD[i]] = 1

max = 0
ans = []
for key, value in dic.items():
  if(value < max):
    continue
  elif(value > max):
    ans = []
    max = value
  ans.append(key)

if(len(ans) != 1):
  print('?')
else:
  print(ans[0])