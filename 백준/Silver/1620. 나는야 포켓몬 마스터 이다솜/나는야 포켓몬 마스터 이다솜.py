n, m = map(int, input().split())

pokemon_name = {}
pokemon_num = {}
ans = []
for i in range(1, n + 1):
  name = input()
  pokemon_name[i] = name
  pokemon_num[name] = str(i)

for _ in range(m):
  tmp = input()
  if(tmp.isalpha()):
    ans.append(pokemon_num[tmp])
  else:
    ans.append(pokemon_name[int(tmp)])

print("\n".join(ans))