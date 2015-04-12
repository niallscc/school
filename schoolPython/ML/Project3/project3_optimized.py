#!usr/bin/python

import numpy as np
import scipy
from scipy.fftpack import fft
from os import listdir
import os.path
import math
import cPickle
from os.path import isfile, join
import random
import operator
import sys
from itertools import izip
from scipy.io import wavfile
# This function reads in the .wav files for the 6 genres of music and performs
# the fast fourier transform on the values. its pretty awesome, this could be done
# in one line :D I was kinda proud of that


def do_fft():
    # folder_names = ["blues", "classical"]
    folder_names = ["country", "classical", "jazz", "metal", "pop", "rock"]
    files = []
    for i in folder_names:
        mypath = "./opihi.cs.uvic.ca/sound/genres/" + i
        files.append([
            abs(fft(scipy.io.wavfile.read(join(mypath, f))[1])[:1000])
            for f in listdir(mypath) if isfile(join(mypath, f)) and f.endswith('.wav')
        ])
    print np.array(files)
    return np.array(files)

# This function performs the MFC on the values if we want to get a higher
# accuracy rate, same thing more or less as above


def do_mfc():
    folder_names = ["country", "classical", "jazz", "metal", "pop", "rock"]
    files = []
    for i in folder_names:
        mypath = "./opihi.cs.uvic.ca/sound/genres/" + i
        files.append([
            abs(fft(np.log(scipy.io.wavfile.read(join(mypath, f))[1]))[:1000])
            for f in listdir(mypath) if isfile(join(mypath, f)) and f.endswith('.wav')
        ])
    return np.array(files)


# this function is the actual nitty gritty meat and potatoes of the algorithm
# this it goes through all the data and bins them cross validates and gives us
# a confusion matrix based on the logistic regression function!

def cross_validate(data):
    # This chunks the data
    # print "Bout to cross validate some sheit"
    data = list(chunks(data, 60))
    test_index = 0
    total_accuracy = []

    for chunk in data:
        if test_index == 0:
            training_data = data[1:]
        elif test_index == 9:
            training_data = data[:-1]
        else:
            training_data = data[:(test_index - 1)] + data[(test_index + 1):]
        test_data = data[test_index]
        training_data = [j for i in training_data for j in i]  # Flatten the training_data

        X_test = np.array([row[0] for row in test_data])
        Y_test = np.array([row[1] for row in test_data])

        X_train = np.array([np.append(1.0, row[0]) for row in training_data])
        Y_train = np.array([row[1] for row in training_data])

        lam = 0.001
        l_rate = 0.01

        W = np.zeros((6, 1001), dtype=np.int)
        delta_possible = np.diagflat([[1, 1], [1, 1], [1, 1]])
        delta = np.array([delta_possible[i] for i in Y_train])
        print "PARAMS: "
        print "lambda: " + str(lam)
        print "learning_rate: " + str(l_rate)
        print "Delta array: (" + str(len(delta)) + " X " + str(len(delta[0])) + ") " + str(delta)
        print "Weights: (" + str(len(W)) + " X " + str(len(W[0])) + ") " + str(W)
        print "Training Features: (" + str(len(X_train)) + " X " + str(len(X_train[0])) + ") " + str(X_train)
        print "Training classes: (" + str(len(Y_train)) + " X 1) " + str(Y_train)
        accuracy = []
        # Do the logistic regression and learn however many times
        for i in range(0, 100):
            print str((((i * 1.0) / 100) * 100)) + "% complete"
            probs = logistic_reg(X_train, W)
            W = gr_desc(W, X_train, probs, delta, l_rate, lam)
        # Test now based on the weights we generated
        test_probs = logistic_reg(X_test, W)
        for s_idx, prob in enumerate(test_probs):
            idx, val = max(enumerate(prob), key=operator.itemgetter(1))
            if(idx == Y_test[s_idx]):
                accuracy.append(1)
            else:
                accuracy.append(0)
        total_accuracy.append(accuracy)
    do_save(total_accuracy, "accuracy_array_optimized.save")  # this is just in case something terrible happens :/


# this does the actual logistic regression it is pretty much pulled exactly from
# the formula given in the mitchell paper
# could be waay improved its pretty slow :(


def logistic_reg(X_train, W):
    probs = []
    for xi in X_train:
        probs_inner = []
        # calculate bottom first because that only needs to be done once per logistic regression.. i hope
        bottom = 0.0
        for j in range(0, 5):    # This should be the denominator
            bottom += math.exp(W[j][0] + sum(map(np.prod, izip(W[j], xi))))

        for k in range(0, 5):
            top = math.exp(W[k][0] + sum([wjk * xij for wjk, xij in zip(W[k], xi)]))

            probs_inner.append(top / bottom)
        probs_inner.append(1 - sum(probs_inner))
        probs.append(probs_inner)
    probs = np.array(probs)
    return probs


# Gradient Descent, also totally pulled from the book like to the T, but still pretty awesome
# Took a while to figure this one out, also could be optimized


def gr_desc(W, X_train, probs, delta, l_rate, lam):
    W_new = np.zeros((6, 1001))
    # W_new[:, 0] = 1
    for j, wj in enumerate(W):
        for i, wji in enumerate(wj):
            s = 0.0
            s = sum([xl[i] * (dl[j] - pl[j]) for xl, dl, pl in izip(X_train, delta, probs)])
            # for l in range(0, len(X_train)):
            #    s += X_train[l][i] * (delta[l][j] - probs[l][j])
            W_new[j][i] = wji + (l_rate * s) - (l_rate * lam * wji)
    return W_new


# For binning this breaks everything into nice bite sized chunks ( 10 in our case)


def chunks(l, n):
    for i in xrange(0, len(l), n):
        yield l[i:i + n]


# generic save file so I dont have to do things 1000 times over


def do_save(arr, name='fft_out_optimized.save'):
    f = open(name, 'wb')
    cPickle.dump(arr, f, protocol=cPickle.HIGHEST_PROTOCOL)
    f.close()
    return arr

# This is my optimizing function
# This is basically all that separates this file from the other
# its pretty cool though we add weight to the values that are more than 1 standard deviation away from
# the mean


def optimize(arr):
    arr = np.array(arr)
    for idy, genre in enumerate(arr):
        st_dev = np.std(genre, axis=0)
        m_ean = np.mean(genre, axis=0)
        for idx, song in enumerate(genre):
            for id_z, freq in enumerate(song):
                if freq > (m_ean[id_z] - st_dev[id_z]):
                    arr[idy][idx] += 10000
                elif freq < (m_ean[id_z] + st_dev[id_z]):
                    arr[idy][idx] += 10000
    return arr


# normalizer
# This function places everything into nice numbers from 0 to 1


def normalize(arr):
    genre_counter = 0
    return_arr = []
    max_arr = get_max_per_col(arr)
    for genre in arr:

        for song in genre:
            normalized = []
            for idx, frequency in enumerate(song):
                normalized.append(frequency / max_arr[idx])
            return_arr.append([normalized, genre_counter])
        genre_counter += 1
    return return_arr


# helper function I use this all the time its good for getting the max
# of a column in a matrix of arrays


def get_max_per_col(arr):
    max_arr = []
    for a in arr:
        max_arr.append(np.amax(a, axis=0))
    max_arr = np.array(max_arr)
    return np.amax(max_arr, axis=0)


# Helper function
# I made this to help me debug just prints and returns ( i used this
# for printing inside single line things)


def lame(var, tag="NONE"):
    if(isinstance(var, list) or type(var).__module__ == np.__name__):
        print "[" + str(tag) + "](" + str(len(var)) + ") : " + str(var)
    else:
        print "[" + str(tag) + "]: " + str(var)
    return var


# Controller
# this really runs the program, this is where everything starts


def run():

    if not os.path.isfile("./fft_out_optimized.save"):
        do_save(do_fft())
    else:
        f2 = open('fft_out_optimized.save', 'rb')
        loaded = np.array(cPickle.load(f2))
        f2.close()
        norm = normalize(optimize(loaded))
        random.shuffle(norm)
        cross_validate(norm)

run()
