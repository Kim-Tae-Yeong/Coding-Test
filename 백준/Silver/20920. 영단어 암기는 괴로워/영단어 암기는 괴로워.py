import sys

n, m = map(int, input().split())

dic = {}
for _ in range(n):
  word = sys.stdin.readline().strip()
  length = len(word)
  if(length >= m):
    if(word in dic):
      dic[word][0] += 1
    else:
      dic[word] = [1, length, word]

tmp = list(dic.values())
tmp.sort(key = lambda x : (-x[0], -x[1], x[2]))

for elem in tmp:
  print(elem[2])