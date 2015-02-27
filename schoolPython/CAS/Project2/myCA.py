#!usr/bin/python
import random
import numpy as np
import math
import operator


def my_ca(rad, str_len):

    radius = rad
    string_len = str_len

    # Our populations of length whatever was passed in
    # These arrays basically look like a huge list of random
    # ones and zeros

    populations = np.array(
        [np.array(
            [random.randint(0, 1) for b in range(1, string_len + 1)]
        ) for b in range(1, 50)])

    # our different lists of random rules. we will eventally turn the indecies
    # into binary from 0 to 2^radus + 1

    fitters = np.array(
        [np.array(
            [random.randint(0, 1) for b in range(1, math.pow(2, radius) + 1)]
        ) for b in range(1, 10)])

    # This calculates the fitness for 200 generations of each rule applied to
    # random bit strings
    for i in range(0, 50):
        rules_with_fitness = []
        for id_fit, fitter in enumerate(fitters):
            fitness_arr = []
            for idx, pop in enumerate(populations):
                for i in range(0, 200):
                    pop = apply_ca(pop, fitter, radius)
                fitness_arr[idx] = calc_fitness(pop)

            rules_with_fitness[id_fit] = {
                'fitness': avg_fitness(fitness_arr),
                'individual': fitter
            }
        fitters = evolve_fitters(rules_with_fitness)


def apply_ca(pop, fitter, radius):
    # This function takes our population and applies our rule set to it

    i = 0
    length = len(pop)
    new_pop = []
    while i < length:
        indecies = get_indecies(i, radius, length)
        bit_string = "0b"
        for ind in indecies:
            bit_string += pop[ind]
        new_pop[i] = fitter[int(bit_string, 2)]
        i = i + 1

    return new_pop


def get_indecies(i, radius, length):
    indecies = []
    for j in range(radius, 0, -1):
        if(i - j < 0):
            indecies.append(length - j + i)
        else:
            indecies.append(abs(j - i))
    indecies.append(i)
    for j in range(1, radius + 1):
        if(j + i >= length):
            indecies.append(j - 1)
        else:
            indecies.append(i + j)
    return indecies


def evolve_fitters(rules_with_fitness):
    evolved = []
    sorted_fit = sorted(
        rules_with_fitness.items(), key=operator.itemgetter('fitness')
    )
    perc = int(len(sorted_fit) * 0.2)
    for i in range(0, len(sorted_fit) - 1):
        if i < perc:
            evolved.append(sorted_fit[i].get('individual'))
        else:
            indiv = sorted_fit[i]['individual']
            pivot = random.randint(0, len(indiv))
            first_split = np.split(indiv, pivot)
            indiv2 = sorted_fit[i + 1]['individual']
            second_split = np.split(indiv2, pivot)
            evolved.append(mutate(first_split[0].append(second_split[1])))
            evolved.append(mutate(first_split[1].append(second_split[0])))

    return evolved


def mutate(arr):
    arr[random.randint(0, len(arr))] = random.randint(0, 1)
    arr[random.randint(0, len(arr))] = random.randint(0, 1)
    return arr


def avg_fitness(fitness):
    tot = 0
    for i in fitness:
        tot += i

    return tot / len(fitness)


def calc_fitness(fit_arr):
    ones = 0
    zeroes = 0
    for i in fit_arr:
        if i == 0:
            zeroes += 1
        else:
            ones += 1
    return ones / len(fit_arr)


my_ca(2, 121)
