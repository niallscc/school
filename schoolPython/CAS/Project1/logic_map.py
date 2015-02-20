#!usr/bin/python
import matplotlib.pyplot as plt
import sys
import json


def reproduction(r, arr, k, iterations):

    if len(arr) >= iterations:
        return arr
    else:
        val = r * arr[-1] * (1 - arr[-1])

        arr.append(val)
        return reproduction(r, arr, k, iterations)

r = 3.9
iters = 20
show_only_divergence = 0
pop_vals_arr = [0.50, .51]

if len(sys.argv) == 2:
    r = float(sys.argv[1])
if len(sys.argv) == 3:
    r = float(sys.argv[1])
    iters = int(sys.argv[2])
if len(sys.argv) == 4:
    r = float(sys.argv[1])
    iters = int(sys.argv[2])
    dumb = sys.argv[3]
    pop_vals_arr = json.loads(dumb)

print "R: %f, Iterations: %i popVals = %s" % (r, iters, str(pop_vals_arr))
f, axarr = plt.subplots(
    len(pop_vals_arr) + len(pop_vals_arr) - 1, sharex=False)

if show_only_divergence == 0:

    for index in range(0, len(pop_vals_arr)):
        arr1 = reproduction(r, [pop_vals_arr[index]], 1, iters)

        axarr[index].plot(arr1, label='Starting Population: %0.2f)' % (
            pop_vals_arr[index]))
    plt.legend(
        bbox_to_anchor=(0., 1.02, 1., .102),
        loc=3, ncol=2, mode="expand", borderaxespad=0.)

for index in range(0, len(pop_vals_arr) - 1):

    arr = reproduction(r, [pop_vals_arr[index]], 1, iters)
    arr2 = reproduction(r, [pop_vals_arr[index + 1]], 1, iters)
    diff_arr = []

    for arr_one, arr_two in zip(arr, arr2):
        diff_arr.append(abs(arr_two - arr_one))

    axarr[len(pop_vals_arr) + index].plot(
        diff_arr,
        label='Starting Popula tion: %0.2f)' % (
            pop_vals_arr[index]))

plt.xlabel("Generations")
plt.show()
