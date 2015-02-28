#!usr/bin/python
import random
import numpy as np
import math
import operator


def my_ca(rad, str_len):

    radius = rad  # should be 2 or 3
    string_len = str_len  # should be 121
    num_generations = 10  # should be 200
    num_evolvs = 1  # Should be 50

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
            [random.randint(0, 1) for b in range(
                1, int(math.pow(2, (radius * 2 + 1)) + 1))]
        ) for b in range(0, 10)])

    # Overwrite the results file each run
    f = open("./results.txt", "w")
    f.write("")

    # This calculates the fitness for 200 generations of each rule applied to
    # random bit strings
    for i in range(0, num_evolvs):
        rules_with_fitness = []
        for id_fit, fitter in enumerate(fitters):
            fitness_arr = []
            for idx, pop in enumerate(populations):
                for i in range(0, num_generations):
                    pop = apply_ca(pop, fitter, radius)
                fitness_arr.append(calc_fitness(pop))

            rules_with_fitness.append({
                'fitness': avg_fitness(fitness_arr),
                'individual': fitter
            })
        write_to_file(rules_with_fitness)
        fitters = evolve_fitters(rules_with_fitness)


def write_to_file(to_write):
    f = open("./results.txt", "a")
    for i in to_write:
        f.write(str(to_write))


def apply_ca(pop, fitter, radius):
    # This function takes our population and applies our rule set to it

    i = 0
    length = len(pop)
    new_pop = []
    while i < length:
        indecies = get_indecies(i, radius, length)
        bit_string = "0b"
        for ind in indecies:
            bit_string += str(pop[ind])
        new_pop.append(fitter[int(bit_string, 2)])
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
    rules_with_fitness.sort(key=operator.itemgetter('fitness'), reverse=True)
    perc = int(len(rules_with_fitness) * 0.2)

    for i in range(0, len(rules_with_fitness) - 1):
        if i < perc:
            evolved.append(rules_with_fitness[i].get('individual'))
        else:
            indiv = rules_with_fitness[i]['individual']
            pivot = random.randint(0, len(indiv))
            first_split = [indiv[:pivot], indiv[pivot:]]
            indiv2 = rules_with_fitness[i + 1]['individual']
            second_split = [indiv2[:pivot], indiv2[pivot:]]
            evolved.append(mutate(np.insert(
                first_split[0], len(first_split[0]), second_split[1])))
            evolved.append(mutate(np.insert(
                second_split[1], len(second_split[1]), first_split[0])))
    return evolved


def mutate(arr):
    arr[random.randint(0, len(arr) - 1)] = random.randint(0, 1)
    arr[random.randint(0, len(arr) - 1)] = random.randint(0, 1)
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
            zeroes = zeroes + 1
        else:
            ones = ones + 1
    return (ones + 0.0) / (len(fit_arr) + 0.0)

my_ca(2, 121)
