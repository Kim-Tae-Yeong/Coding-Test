import sys
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/11724/11724.txt', 'rt')
sys.setrecursionlimit(10 ** 6)

n, m = map(int, sys.stdin.readline().strip().split())
graph = [[] for _ in range(n + 1)]
for _ in range(m):
  u, v = map(int, sys.stdin.readline().strip().split())
  graph[u].append(v)
  graph[v].append(u)

visited = [False for _ in range(n + 1)]
def dfs(start):
  visited[start] = True
  for elem in graph[start]:
    if(not visited[elem]):
      dfs(elem)

ans = 0
for i in range(1, n + 1):
  if(not visited[i]):
    ans += 1
    dfs(i)

print(ans)