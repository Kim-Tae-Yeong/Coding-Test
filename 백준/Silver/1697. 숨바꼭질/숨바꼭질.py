import sys
# sys.stdin = open('/Users/kimtaeyeong/CodingTest/BaekJoon/1697/1697.txt', 'rt')
from collections import deque

n, k = map(int, sys.stdin.readline().strip().split())

ans = [0] * 100001
visited = [False] * 100001
q = deque([[n, 0]])
visited[n] = True
while True:
  info = q.popleft()
  pos = info[0]
  cnt = info[1]
  if(pos == k):
    break
  if(0 <= pos + 1 <= 100000 and not visited[pos + 1]):
    visited[pos + 1] = True
    ans[pos + 1] = cnt + 1
    q.append([pos + 1, cnt + 1])
  if(0 <= pos - 1 <= 100000 and not visited[pos - 1]):
    visited[pos - 1] = True
    ans[pos - 1] = cnt + 1
    q.append([pos - 1, cnt + 1])
  if(0 <= 2 * pos <= 100000 and not visited[2 * pos]):
    visited[2 * pos] = True
    ans[pos * 2] = cnt + 1
    q.append([2 * pos, cnt + 1])
  
print(ans[k])