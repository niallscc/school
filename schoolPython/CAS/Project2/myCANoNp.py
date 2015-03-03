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
    num_fitters = 100  # should be 100
    num_init_config = 50  # should be 50

    fitters = do_lambda(int(math.pow(2, (radius * 2 + 1))), num_fitters)
    # print "fitters are: " + str(fitters)
    # print fitters
    # Overwrite the results file each run
    # Could Probably make this better but meh
    f = open("./results.txt", "w")
    f.write("[")
    gen_break_f = open("./gen_break_result.txt", "a")

    # This calculates the fitness for 200 generations of each rule applied to
    # random bit strings
    for i in range(0, num_evolvs):
        # our different lists of random rules. we will eventally turn the
        # indecies
        # into binary from 0 to 2^radus + 1
        # Our init_config of length whatever was passed in
        # These arrays basically look like a huge list of random
        # ones and zeros
        init_config = do_lambda(string_len, num_init_config)
        # print "Init config is: " + (str(init_config))
        rules_with_fitness = []
        # print fitters
        for id_fit, fitter in enumerate(fitters):
            fitness_arr = []
            for idx, pop in enumerate(init_config):
                broke = False
                for i in range(0, num_generations):
                    new_pop = apply_ca(pop, fitter, radius)
                    if check_equal(new_pop, pop):
                        gen_break_f.write(str(i) + ",")
                        print "Equival: " + str(i)
                        break
                    pop = new_pop
                if broke is not True:
                    gen_break_f.write(str(num_generations) + ",")
                fitness_arr.append(calc_fitness_with_density(pop, fitter))
            avg_fit = avg_fitness(fitness_arr)
            rules_with_fitness.append({
                'fitness': avg_fit,
                'individual': fitter
            })
        write_to_file(rules_with_fitness)
        fitters = evolve_fitters(rules_with_fitness)
    f = open("./results.txt", "a")
    f.write("]")


def check_equal(arr1, arr2):
    equal = True
    for i in range(0, len(arr1)):
        if arr1[i] != arr2[i]:
            equal = False
            break

    return equal


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


def calc_fitness_with_density(fit_arr, arr):
    if float(arr.count(1)) / float(len(arr)) > 0.5:
        check = 1
    else:
        check = 0

    for i in fit_arr:
        if i != check:
            return 0.0
    return 1.0


def do_lambda(size_of_array, num_arrays_needed):

    arrays = []
    num_to_add = (float(size_of_array) / float(num_arrays_needed))
    # add our zeroes first
    buckets = [0] * size_of_array
    arrays.append(buckets)
    # generate the list of zeroes
    calcer = num_to_add
    for i in range(1, num_arrays_needed - 1):
        calcer = num_to_add + calcer
        if calcer < 1:
            buckets = [1] * 1
            buckets.extend(([0] * (size_of_array - 1)))
        else:
            buckets = [1] * int(calcer)
            buckets.extend(([0] * (size_of_array - int((calcer)))))

        random.shuffle(buckets)
        arrays.append(buckets)
    # add our 1s last (for sanity)
    buckets = [1] * size_of_array

    arrays.append(buckets)
    return arrays
my_ca(2, 121)
