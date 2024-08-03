n = int(input())
tmp = set([])
for _ in range(n):
  word = input()
  tmp.add(word)

ans = list(tmp)
ans.sort(key = lambda x : (len(x), x))
print('\n'.join(ans))