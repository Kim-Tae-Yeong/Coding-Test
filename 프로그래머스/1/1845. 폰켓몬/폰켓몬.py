def solution(nums):
    tmp = set(nums)
    answer = len(tmp)
    cnt = len(nums) // 2
    if(answer > cnt):
        answer = cnt
    return answer