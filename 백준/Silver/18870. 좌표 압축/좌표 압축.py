import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/18870/18870.txt', 'rt')

n = int(sys.stdin.readline().strip())
x = list(map(int, sys.stdin.readline().strip().split()))
ans = [0] * n

dic = {}
for i, elem in enumerate(x):
  if(elem not in dic):
    dic[elem] = [i]
  else:
    dic[elem].append(i)
  
x.sort()
cnt = 0
for i in range(n - 1):
  if(x[i] < x[i + 1]):
    for idx in dic[x[i]]:
      ans[idx] = str(cnt)
    cnt += 1
  else:
    continue

for idx in dic[max(x)]:
  ans[idx] = str(cnt)

print(' '.join(ans))