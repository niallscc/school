#!usr/bin/python


def calc_fitness_with_density(fit_arr, arr):
    if float(arr.count(1)) / float(len(arr)) > 0.5:
        check = 1
    else:
        check = 0

    for i in fit_arr:
        if i != check:
            return 0.0
    return 1.0

arr = [0, 0, 0, 0, 0, 0]
arr2 = [1, 0, 0, 0, 1, 1, 0]
print calc_fitness_with_density(arr, arr2)
