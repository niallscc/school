#!usr/bin/python
import random


def do_lambda(size_of_array, num_arrays_needed):

    arrays = []
    num_to_add = (float(size_of_array) / float(num_arrays_needed))
    # add our zeroes first
    buckets = [0] * size_of_array
    arrays.append(buckets)
    print buckets.count(1)
    print buckets
    print "density = " + str(float(buckets.count(1)) / float(size_of_array))
    print "\n"
    # generate the list of zeroes
    calcer = num_to_add
    for i in range(1, num_arrays_needed - 1):
        calcer = num_to_add + calcer
        buckets = [1] * int(calcer)
        buckets.extend(([0] * (size_of_array - int((calcer)))))
        random.shuffle(buckets)
        arrays.append(buckets)
        print buckets
        print "density = " + str(float(buckets.count(1)) / float(size_of_array))
    # add our 1s last (for sanity)
    buckets = [1] * size_of_array

    arrays.append(buckets)
    print buckets
    print "density = " + str(float(buckets.count(1)) / float(size_of_array))
    return arrays

do_lambda(32, 100)
