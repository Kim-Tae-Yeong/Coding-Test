import heapq as h

def solution(scoville, K):
    answer = 0
    length = len(scoville)
    h.heapify(scoville)
    while(scoville[0] < K):
        if(length == 1):
            answer = -1
            break
        answer += 1
        no_hot_1 = h.heappop(scoville)
        no_hot_2 = h.heappop(scoville)
        mix = no_hot_1 + (no_hot_2 * 2)
        h.heappush(scoville, mix)
        length -= 1
    return answer