def dfs(r, c, prev_dir, cost):
    if r == n - 1:
        candidate.append(cost)
        return
    
    # 현재 위치에서 다음 위치로 이동
    for direction in range(3):
        nr = r + 1
        nc = c + (direction - 1)
        
        # 새로운 좌표가 유효한지 확인
        if 0 <= nc < m and direction != prev_dir:
            new_cost = cost + board[nr][nc]
            dfs(nr, nc, direction, new_cost)

# 입력 받기
n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

candidate = []

# 첫 번째 행에서 시작
for col in range(m):
    dfs(0, col, -1, board[0][col])

print(min(candidate))
