def solution(participant, completion):
    answer = ''
    dic = {}
    for elem in participant:
        if elem in dic:
            dic[elem] += 1
        else:
            dic[elem] = 1
    for elem in completion:
        dic[elem] -= 1
    for key, value in dic.items():
        if value == 1:
            answer += key
            break
    return answer