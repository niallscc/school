#!usr/bin/python
import random


def do_lambda(size_of_array, num_arrays_needed):

    arrays = []
    num_to_add = (float(size_of_array) / float(num_arrays_needed))
    # add our zeroes first
    buckets = [0] * size_of_array
    arrays.append(buckets)
    # generate the list of zeroes
    calcer = num_to_add
    for i in range(1, num_arrays_needed - 1):
        calcer = num_to_add + calcer
        buckets = [1] * int(calcer)
        buckets.extend(([0] * (size_of_array - int((calcer)))))
        random.shuffle(buckets)
        arrays.append(buckets)
    # add our 1s last (for sanity)
    buckets = [1] * size_of_array

    arrays.append(buckets)
    return arrays

print do_lambda(121, 50)
