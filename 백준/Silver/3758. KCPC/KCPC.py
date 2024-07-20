T = int(input())
ans = []

for _ in range(T):
  n, k, t, m = map(int, input().split())
  score = [[0 for _ in range(k + 3)] for _ in range(n)]
  for idx in range(n):
    score[idx][0] = idx + 1
  for idx in range(m):
    i, j, s = map(int, input().split())
    if(score[i - 1][j] < s):
      score[i - 1][j] = s
    score[i - 1][k + 1] += 1
    score[i - 1][k + 2] = idx

  score.sort(key = lambda x : (-sum(x[1 : k + 1]), x[k + 1], x[k + 2]))
  for idx, elem in enumerate(score):
    if(elem[0] == t):
      ans.append(idx + 1)

for elem in ans:
  print(elem)