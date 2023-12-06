def solution(clothes):
    answer = 1
    dic = {}
    for elem in clothes:
        if elem[1] in dic.keys():
            dic[elem[1]] += 1
        else:
            dic[elem[1]] = 1
    for value in dic.values():
        answer *= (value + 1)
    return (answer - 1)