#!usr/bin/python

import numpy as np
import matplotlib.pyplot as plt


def plot_accuracy():

    x_vals, y_vals = read_file("./data/accuracy_vals.txt")
    plt.semilogx(x_vals, y_vals)
    plt.title('Retrained Naive Bayes')
    plt.ylabel("Accuracy")
    plt.xlabel("Log 0.00001 to 1.0")
    plt.grid(True)
    plt.show()


def read_file(filename):
    "./data/accuracy_vals.txt"
    x_vals = []
    y_vals = []
    with open(filename) as f:
        content = f.readlines()
        for line in content:
            splitted = line.split()
            splitted = [
                float(numeric_string) for numeric_string in splitted
            ]
            # if len(splitted) == 1:
            x_vals.append(splitted[0])
            y_vals.append(splitted[1])
            # else:
            #    data.append(splitted)
    return x_vals, y_vals

plot_accuracy()
