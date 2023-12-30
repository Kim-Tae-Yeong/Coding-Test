def solution(bridge_length, weight, truck_weights):
    check = truck_weights[0]
    cnt = 1
    answer = 1
    length = len(truck_weights)
    time = [0 for _ in range(length)]
    idx = 1
    start = -1
    while(time[-1] < bridge_length):
        for i in range(idx):
            if(time[i] < bridge_length):
                time[i] += 1
        answer += 1
        for i in range(start, idx):
            if(time[i] == bridge_length and i > start):
                check -= truck_weights[i]
                cnt -= 1
                start = i
                break
        if(idx < length and check + truck_weights[idx] <= weight and cnt < bridge_length):
            check += truck_weights[idx]
            cnt += 1
            idx += 1
    return answer