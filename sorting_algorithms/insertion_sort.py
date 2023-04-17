def insertion_sort(A: list, n: int) -> list:
    # A: input array
    # n: len(A)

    for k in range(1, n):
        key = A[k]
        i = k
        while i > 0 and A[i - 1] > key:
            A[i] = A[i - 1]
            i = i - 1
        A[i] = key
    return A
