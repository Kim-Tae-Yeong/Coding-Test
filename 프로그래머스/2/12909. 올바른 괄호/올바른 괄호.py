def solution(s):
    answer = True
    length = len(s)
    cnt = 0
    for i in range(length):
        if(s[i] == '('):
            cnt += 1
        else:
            cnt -= 1
            if(cnt < 0):
                answer = False
                break
    if(cnt != 0):
        answer = False
    return answer