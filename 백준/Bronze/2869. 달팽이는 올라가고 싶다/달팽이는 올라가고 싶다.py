a, b, v = map(int, input().split())
total_dis = v - a
day_dis = a - b
if(total_dis % day_dis == 0):
  day = total_dis // day_dis
else:
  day = (total_dis // day_dis) + 1

print(day + 1)