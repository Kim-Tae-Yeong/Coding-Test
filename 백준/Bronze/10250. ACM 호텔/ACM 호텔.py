t = int(input())

ans = []
for _ in range(t):
  h, w, n = map(int, input().split())
  if(n % h == 0):
    height = h
    num = n // h
  else:
    height = n % h
    num = (n // h) + 1
  if(num < 10):
    num = '0' + str(num)
  else:
    num = str(num)
  ans.append(str(height) + num)

print('\n'.join(ans))