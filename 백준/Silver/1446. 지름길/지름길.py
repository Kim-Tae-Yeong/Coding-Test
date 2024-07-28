import sys

n, d = map(int, input().split())
shortcuts = []

for _ in range(n):
    start, end, length = map(int, sys.stdin.readline().strip().split())
    if end <= d and length < end - start:
        shortcuts.append((start, end, length))

distance = list(range(d + 1))

for i in range(d + 1):
    if i > 0:
        distance[i] = min(distance[i], distance[i - 1] + 1)
    for start, end, length in shortcuts:
        if i == start and distance[i] + length < distance[end]:
            distance[end] = distance[i] + length

print(distance[d])