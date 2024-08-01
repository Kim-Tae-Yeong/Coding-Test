remain = [0 for _ in range(42)]

for _ in range(10):
  num = int(input())
  remain[num % 42] = 1

print(sum(remain))