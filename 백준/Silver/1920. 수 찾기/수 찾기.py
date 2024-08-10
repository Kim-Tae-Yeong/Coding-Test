n = int(input())
tmp = list(map(int, input().split()))
tmp.sort()

m = int(input())
check = list(map(int, input().split()))

ans = []

def binary_search(num):
  start = 0
  end = n - 1
  have = 0
  while(start <= end):
    mid = (start + end) // 2
    if(tmp[mid] == num):
      ans.append('1')
      have = 1
      break
    elif(tmp[mid] > num):
      end = mid - 1
    elif(tmp[mid] < num):
      start = mid + 1
  if(have == 0):
    ans.append('0')

for elem in check:
  binary_search(elem)

print('\n'.join(ans))