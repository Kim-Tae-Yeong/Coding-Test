def solution(genres, plays):
    answer = []
    num = [i for i in range(len(genres))]
    dic = {}
    for idx, genre, play in zip(num, genres, plays):
        if genre in dic.keys():
            dic[genre][0] += play
            dic[genre][1].append([idx, play])
        else:
            tmp = [0, []]
            tmp[0] += play
            tmp[1].append([idx, play])
            dic[genre] = tmp
    value = list(dic.values())
    value.sort(key = lambda x : (-x[0]))
    for elem in value:
        length = len(elem[1])
        elem[1].sort(key = lambda x : -x[1])
        answer.append(elem[1][0][0])
        if(length > 1):
            answer.append(elem[1][1][0])
    return answer