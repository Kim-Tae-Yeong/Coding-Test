ans = []
tmp = [0, 0, 0]

while True:  
  a, b, c = map(int, input().split())
  tmp[0] = a
  tmp[1] = b
  tmp[2] = c
  tmp.sort(reverse = True)
  if(a == 0):
    break
  if(tmp[0] >= tmp[1] + tmp[2]):
    ans.append("Invalid")
  elif(tmp[0] == tmp[2]):
    ans.append("Equilateral")
  elif(tmp[0] == tmp[1] or tmp[1] == tmp[2]):
    ans.append("Isosceles")
  else:
    ans.append("Scalene")
  

for elem in ans:
  print(elem)