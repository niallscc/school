#!usr/bin/python
import matplotlib.pyplot as plt
import math


def reproduction(r, arr, k, iterations):

    if len(arr) >= iterations:
        return arr
    else:
        val = (r * (arr[-1] - ((arr[-1] * arr[-1]) / k)))

        arr.append(val)
        return reproduction(r, arr, k, iterations)

r = 3.0
iters = 100
pop_init = [510]

# arr1 = reproduction(r, pop_init, 1024, iters)
for j in range(3, 9):
    r = 3 + j * .1
    pop_end_vals = []
    for i in range(0, 500):
        arr1 = reproduction(r, [i], 1024, iters)
        pop_end_vals.append(arr1[-1])
    plt.plot(pop_end_vals, label="R value of %0.2f" % r)
plt.xlabel("Starting Population")
plt.ylabel("Population after 100 generations")
plt.title("Population of rabbits distributed over different R Values and an initial Population values")
plt.show()

val = 0

for i in range(1, 1024):
    div = (1.0 / 1024.0)
    val = val + div * math.log(div, 2)

print "the val is %f " % -val

val = 0

for i in range(508, 513):
    div = (i / 1024.0)
    val = val + div * math.log(div, 2)

print "the val is %f " % -val
