n = int(input())
size = list(map(int, input().split()))
t, p = map(int, input().split())

shirt = 0
for elem in size:
  if(elem % t == 0):
    shirt += elem // t
  else:
    shirt += (elem // t) + 1

print(shirt)
print(n // p, n % p)