s = input()
length = len(s)
cnt0 = 0
cnt1 = 0
for elem in s:
  if(elem == '0'):
    cnt0 += 1
  elif(elem == '1'):
    cnt1 += 1
cnt0 = cnt0 // 2
cnt1 = cnt1 // 2

tmp = [1 for _ in range(length)]
ans = ''

for i in range(length - 1, -1, -1):
  if(s[i] == '0'):
    tmp[i] = 0
    cnt0 -= 1
    if(cnt0 == 0):
      break

for i in range(length):
  if(s[i] == '1'):
    tmp[i] = 0
    cnt1 -= 1
    if(cnt1 == 0):
      break

for i in range(length):
  if(tmp[i] == 1):
    ans += s[i]

print(ans)