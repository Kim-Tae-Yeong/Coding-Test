n, m, b = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

min_height = min(map(min, board))
max_height = max(map(max, board))

# 각 높이별로 블록의 개수를 미리 계산합니다.
height_count = [0] * 257
for i in range(n):
    for j in range(m):
        height_count[board[i][j]] += 1

ans_time = float('inf')
ans_height = -1

# 최대 높이부터 최소 높이까지 역순으로 탐색합니다.
# 블록을 제거할 때 시간이 더 많이 소모되기 때문에 높은 높이부터 탐색하여 같은 시간일 경우 더 높은 높이를 선택하게 합니다.
for h in range(min_height, max_height + 1):
    remove_blocks = 0  # 블록을 제거하는 데 필요한 개수
    add_blocks = 0     # 블록을 추가하는 데 필요한 개수
    
    for height in range(257):
        if height > h:
            remove_blocks += (height - h) * height_count[height]
        else:
            add_blocks += (h - height) * height_count[height]

    if remove_blocks + b >= add_blocks:  # 가지고 있는 블록 수로 가능한 경우
        time = 2 * remove_blocks + add_blocks
        if time < ans_time:
            ans_time = time
            ans_height = h
        elif time == ans_time and h > ans_height:
            ans_height = h

print(ans_time, ans_height)
