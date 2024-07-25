n, k = map(int, input().split())
seq = list(map(int, input().split()))
dic = {}
idx = 0
start_idx = [0 for _ in range(1000000)]
tmp = 0
ans = []

while(idx < n):
  if(seq[idx] in dic):
    dic[seq[idx]] += 1
  else:
    start_idx[seq[idx]] = idx
    dic[seq[idx]] = 1
  if(dic[seq[idx]] == k + 1):
    ans.append(tmp)
    dic = {}
    tmp = 0
    idx = start_idx[seq[idx]] + 1
  else:
    tmp += 1
    idx += 1
ans.append(tmp)

print(max(ans))