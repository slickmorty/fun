def binary_search(input_list: list[int], value: int) -> bool:

    middle = len(input_list)//2

    if input_list[-1] == value:
        return True

    elif input_list[-1] < value:
        return False

    elif input_list[middle] > value:
        return binary_search(input_list=input_list[:middle], value=value)

    else:
        return binary_search(input_list=input_list[middle:], value=value)


if __name__ == "__main__":

    my_list = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
               41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]

    print(binary_search(my_list, 9))
