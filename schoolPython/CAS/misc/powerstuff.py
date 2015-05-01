#!usr/bin/python

import plfit
from numpy.random import rand, seed
import matplotlib.pyplot as plt


def read_file(filename, are_ints):
    data = []
    with open(filename) as f:
        content = f.readlines()
        for line in content:
            splitted = line.split()
            if are_ints is True:
                splitted = [
                    int(numeric_string) for numeric_string in splitted
                ]
            if len(splitted) == 1:
                data.append(float(splitted[0]))
            else:
                data.append(splitted)
    return data


# data = read_file("quakes.txt", False)
# alpha = plfit.discrete_best_alpha(data)
# plfit.plplot(alpha)
# print data


# generate a power law using the "inverse" power-law generator code
# X = plfit.plexp_inv(rand(1000), 1, 2.5)
X = read_file("blackouts.txt", False)
print X
# use the numpy version to fit (usefortran=False is only needed if you installed the fortran version)
myplfit = plfit.plfit(X, usefortran=False)

# output should look something like this:
# PYTHON plfit executed in 0.201362 seconds
# xmin: 0.621393 n(>xmin): 263 alpha: 2.39465 +/- 0.0859979   Log-Likelihood: -238.959   ks: 0.0278864 p(ks): 0.986695
#print myplfit
# generate some plots

f1 = plt.figure()
res = myplfit.plot_lognormal_cdf()
plt.plot(myplfit.plot_lognormal_cdf())
plt.show()
# f2 = figure(2)
# myplfit.plotcdf()
# f2.show()
