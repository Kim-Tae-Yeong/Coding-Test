n, x = map(int, input().split())

num = list(map(int, input().split()))

ans = []
for elem in num:
  if(elem < x):
    ans.append(str(elem))

print(' '.join(ans))