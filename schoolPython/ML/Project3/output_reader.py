#!usr/bin/python
from os import listdir
from os.path import isfile, join
import os.path
import numpy as np
import cPickle


def read_input(arr):
    print len(arr[0])
    print arr
    acc = []
    for i in arr:
        acc.append((sum(i) * 1.0) / len(i))
    print "Total accuracy was: " + str((sum(acc) * 1.0) / len(acc))

    return 0


def run():
    if not os.path.isfile("./accuracy_array_mfcc.save"):
        print "No output file available"
    else:
        f2 = open('accuracy_array_mfcc.save', 'rb')
        loaded = np.array(cPickle.load(f2))
        f2.close()
        read_input(loaded)

run()
