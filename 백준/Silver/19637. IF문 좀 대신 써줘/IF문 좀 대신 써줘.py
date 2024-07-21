import sys
input = sys.stdin.read

def main():
    data = input().split()
    n, m = int(data[0]), int(data[1])
    
    rank_boundary = []
    index = 2
    for _ in range(n):
        r = data[index]
        b = int(data[index + 1])
        if not rank_boundary or rank_boundary[-1][1] != b:
            rank_boundary.append((r, b))
        index += 2

    scores = list(map(int, data[2 + 2 * n:]))
    ans = []
    
    length = len(rank_boundary)
    
    for score in scores:
        # 이진 탐색 구현
        start, end = 0, length
        while start < end:
            mid = (start + end) // 2
            if score <= rank_boundary[mid][1]:
                end = mid
            else:
                start = mid + 1
        ans.append(rank_boundary[start][0])
    
    print('\n'.join(ans))

if __name__ == "__main__":
    main()
