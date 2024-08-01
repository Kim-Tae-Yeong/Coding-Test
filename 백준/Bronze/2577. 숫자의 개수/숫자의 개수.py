a = int(input())
b = int(input())
c = int(input())

num = str(a * b * c)
cnt = [0 for _ in range(10)]
for elem in num:
  cnt[int(elem)] += 1

for elem in cnt:
  print(elem)