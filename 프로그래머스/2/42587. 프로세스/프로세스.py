def solution(priorities, location):
    answer = 0
    board = []
    len_board = len(board)
    idx = 0
    len_priorities = len(priorities)
    while(len_board != len_priorities):
        if(priorities[idx % len_priorities] != 0):
            tmp = priorities[idx % len_priorities]
            check = False
            for i in range(1, len_priorities):
                if(tmp < priorities[(idx + i) % len_priorities]):
                    check = True
                    break
            if(not check):
                priorities[idx % len_priorities] = 0
                board.append(idx % len_priorities)
                len_board += 1
        idx += 1
    for index, elem in enumerate(board):
        if(elem == location):
            answer = index + 1
            break
    return answer