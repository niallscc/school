#!usr/bin/python
import random
import math
import operator
import copy


def my_ca(rad, str_len):

    radius = rad  # should be 2 or 3
    string_len = str_len  # should be 121
    num_generations = 200  # should be 200
    num_evolvs = 50  # Should be 50
    num_fitters = 10  # should be 10
    num_populations = 50  # should be 50

    # Our populations of length whatever was passed in
    # These arrays basically look like a huge list of random
    # ones and zeros

    populations = [
        [random.randint(0, 1)
            for b in range(1, string_len + 1)]
        for b in range(0, num_populations)]

    # our different lists of random rules. we will eventally turn the indecies
    # into binary from 0 to 2^radus + 1

    fitters = [
        [random.randint(0, 1)
            for b in range(1, int(math.pow(2, (radius * 2 + 1)) + 1))]
        for b in range(0, num_fitters)]
    # print fitters
    # Overwrite the results file each run
    # Could Probably make this better but meh
    f = open("./results.txt", "w")
    f.write("[")
    gen_break = []
    # This calculates the fitness for 200 generations of each rule applied to
    # random bit strings
    for i in range(0, num_evolvs):
        rules_with_fitness = []
        # print fitters
        for id_fit, fitter in enumerate(fitters):
            print id_fit
            print fitter
            fitness_arr = []
            for idx, pop in enumerate(populations):
                broke = False
                for i in range(0, num_generations):
                    new_pop = apply_ca(pop, fitter, radius)
                    if(pop == new_pop):
                        gen_break.append(i)
                        broke = True
                        break
                if broke is not True:
                    gen_break.append(200)
                fitness_arr.append(calc_one_hund_fitness(pop))
            # print avg_fitness(fitness_arr)
            # print fitter
            rules_with_fitness.append({
                'fitness': avg_fitness(fitness_arr),
                'individual': fitter
            })
        write_to_file(rules_with_fitness)
        fitters = evolve_fitters(rules_with_fitness)
    f = open("./results.txt", "a")
    f.write("]")

    gen_break_f = open("./gen_break_result.txt", "w")
    gen_break_f.write(str(gen_break))


# Generic output file of dumbness
def write_to_file(to_write):
    f = open("./results.txt", "a")
    f.write(str(to_write) + ",")


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
        try:
            new_pop.append(fitter[int(bit_string, 2)])
        except IndexError:
            print "index error on "
            print bit_string
            print "fitter is: "
            print fitter
            new_pop.append(0)

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

    for i in range(0, perc):
        evolved.append(rules_with_fitness[i].get('individual'))
        rules_with_fitness.pop()
    for i in range(0, len(rules_with_fitness) - 1, 2):

            indiv = rules_with_fitness[i]['individual']
            pivot = random.randint(0, len(indiv))
            first_split = [indiv[:pivot], indiv[pivot:]]

            indiv2 = rules_with_fitness[i + 1]['individual']
            second_split = [indiv2[:pivot], indiv2[pivot:]]

            c1 = copy.copy(first_split[0])
            c2 = copy.copy(second_split[1])
            c1.extend(c2)
            second_split[1].extend(first_split[0])

            evolved.append(mutate(c1))
            evolved.append(mutate(second_split[1]))
            i = i + 1
    print evolved
    return evolved


def mutate(arr):  # we mutate two bits in each of them

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


def calc_one_hund_fitness(fit_arr):

    for i in fit_arr:
        if i == 0:
            return 0.0
    return 1.0

my_ca(2, 121)
