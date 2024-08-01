h, m = map(int, input().split())

if(m - 45 < 0):
  if(h == 0):
    hour = 23
  else:
    hour = h - 1
  minute = m + 15
else:
  hour = h
  minute = m - 45

print(hour, minute)