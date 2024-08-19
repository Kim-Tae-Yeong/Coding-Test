import sys
from collections import deque
# sys.stdin = open('/Users/kimtaeyeong/BaekJoon/2146/2146.txt', 'rt')

n = int(sys.stdin.readline().strip())
board = [list(map(int, sys.stdin.readline().strip().split())) for _ in range(n)]
visitied = [[False] * n for _ in range(n)]

# 섬 구분 및 가장자리 좌표 저장
def bfs_find_land(r, c, land_num):
    q = deque()
    q.append((r, c))
    visitied[r][c] = True
    board[r][c] = land_num
    edge = []
    while q:
        x, y = q.popleft()
        is_edge = False
        for dx, dy in [(-1,0), (1,0), (0,-1), (0,1)]:
            nx, ny = x + dx, y + dy
            if 0 <= nx < n and 0 <= ny < n:
                if board[nx][ny] == 1 and not visitied[nx][ny]:
                    visitied[nx][ny] = True
                    board[nx][ny] = land_num
                    q.append((nx, ny))
                elif board[nx][ny] == 0:
                    is_edge = True
        if is_edge:
            edge.append((x, y))
    return edge

# 섬을 찾고 경계 좌표 저장
total_land = []
land_num = 2
for i in range(n):
    for j in range(n):
        if board[i][j] == 1 and not visitied[i][j]:
            total_land.append(bfs_find_land(i, j, land_num))
            land_num += 1

# 두 섬 사이의 최단 거리 계산
def find_min_distance():
    ans = sys.maxsize
    for i in range(len(total_land) - 1):
        for j in range(i + 1, len(total_land)):
            for x1, y1 in total_land[i]:
                for x2, y2 in total_land[j]:
                    ans = min(ans, abs(x1 - x2) + abs(y1 - y2) - 1)
    return ans

print(find_min_distance())
