def solution(prices):
    answer = []
    length = len(prices)
    for i in range(length - 1):
        time = 1
        for j in range(i + 1, length):
            if(prices[i] > prices[j]):
                answer.append(time)
                break
            else:
                if(j == length - 1):
                    answer.append(time)
                else:
                    time += 1
    answer.append(0)
    return answer