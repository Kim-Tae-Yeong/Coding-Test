num = 0
idx = 0
for i in range(3):
  word = input()
  if(num == 0):
    if(str.isdigit(word)):
      num = int(word)
      idx = i

ans = num + (3 - idx)
if(ans % 15 == 0):
  print('FizzBuzz')
elif(ans % 3 == 0):
  print('Fizz')
elif(ans % 5 == 0):
  print('Buzz')
else:
  print(ans)