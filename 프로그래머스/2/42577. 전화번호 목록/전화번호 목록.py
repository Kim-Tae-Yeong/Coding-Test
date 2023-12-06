def solution(phone_book):
    phone_book.sort()
    length = len(phone_book)
    for i in range(length - 1):
        check = len(phone_book[i])
        for j in range(i + 1, length):
            # if(phone_book[i][0] != phone_book[j][0]):
            #     break
            if(phone_book[j][: check] > phone_book[i]):
                break
            elif(phone_book[j][: check] == phone_book[i]):
                return False
    return True