import sys

n = int(input())
pos = []
neg = []
for _ in range(n):
  num = int(sys.stdin.readline().strip())
  if(num >= 0):
    pos.append(num)
  else:
    neg.append(num)

pos.sort()
neg.sort(reverse = False)
for elem in neg:
  print(elem)
for elem in pos:
  print(elem)