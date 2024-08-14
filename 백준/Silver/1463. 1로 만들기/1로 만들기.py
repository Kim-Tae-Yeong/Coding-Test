x = int(input())
if(x == 1):
  print(0)
elif(x == 2 or x == 3):
  print(1)
else:
  ans = [0, 0, 1, 1]
  for i in range(4, x + 1):
    if(i % 6 == 0):
      ans.append(min(ans[i - 1], ans[i // 3], ans[i // 2]) + 1)
    elif(i % 3 == 0):
      ans.append(min(ans[i - 1], ans[i // 3]) + 1)
    elif(i % 2 == 0):
      ans.append(min(ans[i - 1], ans[i // 2]) + 1)
    else:
      ans.append(ans[i - 1] + 1)
  print(ans[-1])