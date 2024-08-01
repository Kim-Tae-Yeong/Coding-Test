ans = []

while True:
  length = list(map(int, input().split()))
  if(length[0] == 0):
    break
  length.sort()
  if((length[0] * length[0]) + (length[1] * length[1]) == (length[2] * length[2])):
    ans.append('right')
  else:
    ans.append('wrong')

print('\n'.join(ans))