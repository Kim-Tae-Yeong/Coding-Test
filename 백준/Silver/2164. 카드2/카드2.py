from collections import deque as d

n = int(input())

card = d([i for i in range(n, 0, -1)])

while(n > 1):
  card.pop()
  n -= 1
  card.appendleft(card.pop())

print(card[0])