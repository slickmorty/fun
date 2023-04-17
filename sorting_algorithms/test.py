import random
from insertion_sort import insertion_sort

A = random.sample(range(1, 100), 10)

print(f"{A=}")
print(f"A={insertion_sort(A,len(A))}")
