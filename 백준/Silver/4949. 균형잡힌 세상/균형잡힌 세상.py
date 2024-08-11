import sys

ans = []
while True:
  sentence = list(sys.stdin.readline().rstrip())
  parenthesis = []
  check = 0
  if(sentence[0] == '.'):
    break
  for elem in sentence:
    if(elem == '(' or elem == '['):
      parenthesis.append(elem)
    elif(elem == ')'):
      if(len(parenthesis) == 0 or parenthesis[-1] == '['):
        ans.append('no')
        check = 1
        break
      parenthesis.pop(-1)
    elif(elem == ']'):
      if(len(parenthesis) == 0 or parenthesis[-1] == '('):
        ans.append('no')
        check = 1
        break
      parenthesis.pop(-1)
  if(check == 0):
    if(len(parenthesis) == 0):
      ans.append('yes')
    else:
      ans.append('no')

print('\n'.join(ans))