s = input()
length = len(s)

ans = [-1 for _ in range(26)]

for i in range(length - 1, -1, -1):
  ans[ord(s[i]) - 97] = i

for elem in ans:
  print(elem, end = ' ')