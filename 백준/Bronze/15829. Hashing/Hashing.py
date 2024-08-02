r = 31
m = 1234567891

l = int(input())
word = input()

ans = 0
for i in range(l):
  ans += (ord(word[i]) -96) * (r ** i)

print(ans % m)