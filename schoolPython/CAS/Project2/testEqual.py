#!usr/bin/python

print "check equal"

arr1 = [1, 0, 1, 0, 1, 1]
arr2 = [1, 0, 1, 0, 1, 1]


def check_equal(arr1, arr2):
    equal = True
    for i in range(0, len(arr1)):
        if arr1[i] != arr2[i]:
            equal = False
            break
    return equal

if check_equal(arr1, arr2):
    print "true"
else:
    print "false"
