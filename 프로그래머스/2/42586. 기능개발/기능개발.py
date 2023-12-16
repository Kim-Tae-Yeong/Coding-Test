def solution(progresses, speeds):
    answer = []
    tmp = []
    for progress, speed in zip(progresses, speeds):
        cnt = 0
        while progress < 100:
            progress += speed
            cnt += 1
        tmp.append(cnt)
    length = len(tmp)
    cnt = 1
    idx = 0
    for i in range(1, length):
        check = tmp[idx]
        if(tmp[i] > check):
            answer.append(cnt)
            idx = i
            cnt = 1
        else:
            cnt += 1
        if(i == length - 1):
            answer.append(cnt)
    return answer