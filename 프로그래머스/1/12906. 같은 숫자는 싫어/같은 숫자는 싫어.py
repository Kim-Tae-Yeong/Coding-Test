def solution(arr):
    answer = []
    answer.append(arr[0])
    length = len(arr)
    for i in range(1, length):
        if(answer[-1] == arr[i]):
            continue
        answer.append(arr[i])
    return answer