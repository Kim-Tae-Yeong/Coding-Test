import sys

vowel = ['a', 'e', 'i', 'o', 'u']
tmp = []
length = 0

while True:
  password = sys.stdin.readline().strip()
  if(password == "end"):
    break
  tmp.append(password)
  length += 1

cnt = [1 for _ in range(length)]
ans = ["acceptable." for _ in range(length)]

for idx, elem in enumerate(cnt):
  if(elem == 1):
    check = 0
    for ch in tmp[idx]:
      if ch in vowel:
        check = 1
        break
    if(check == 0):
      ans[idx] = "not acceptable."
      cnt[idx] = 0

for idx, elem in enumerate(cnt):
  if(elem == 1):
    length = len(tmp[idx])
    if(length >= 3):
      for i in range(length - 2):
        if((tmp[idx][i] in vowel) and (tmp[idx][i + 1] in vowel) and (tmp[idx][i + 2] in vowel)):
          ans[idx] = "not acceptable."
          cnt[idx] = 0
          break
        elif((tmp[idx][i] not in vowel) and (tmp[idx][i + 1] not in vowel) and (tmp[idx][i + 2] not in vowel)):
          ans[idx] = "not acceptable."
          cnt[idx] = 0
          break

for idx, elem in enumerate(cnt):
  if(elem == 1):
    length = len(tmp[idx])
    if(length >= 2):
      for i in range(length - 1):
        if(tmp[idx][i] == tmp[idx][i + 1]):
          if((tmp[idx][i] != "e") and (tmp[idx][i] != "o")):
            ans[idx] = "not acceptable."
            cnt[idx] = 0
            break

for pw, answer in zip(tmp, ans):
  print(f"<{pw}> is {answer}")