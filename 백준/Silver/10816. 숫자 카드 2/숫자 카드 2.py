n = int(input())
dic = {}
num = list(map(int, input().split()))
for elem in num:
  if(elem not in dic):
    dic[elem] = 1
  else:
    dic[elem] += 1
m = int(input())
ans = []
check = list(map(int, input().split()))
for tmp in check:
  if(tmp not in dic):
    ans.append('0')
  else:
    ans.append(str(dic[tmp]))

print(' '.join(ans))