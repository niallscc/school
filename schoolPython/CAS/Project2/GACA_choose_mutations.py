#!usr/bin/python
import random
import math
import operator
import copy


fitness_evolved = []


def my_ca(rad, str_len, evolv):

    radius = rad  # should be 2 or 3
    string_len = str_len  # should be 121
    num_generations = 200  # should be 200
    num_evolvs = int(evolv)  # Should be 50
    num_rules = 100  # should be 100
    num_init_config = 50  # should be 50
    # print "num evolvs is: " + str(num_evolvs)
    rules = do_lambda(int(math.pow(2, (radius * 2 + 1))), num_rules)

    # print "rules are: " + str(rules)
    # print rules
    # Overwrite the results file each run
    # Could Probably make this better but meh
    f = open("./results_chosen_num_mutations.txt", "w")
    f.write("[")

    # This calculates the fitness for 200 generations of each rule applied to
    # random bit strings
    f = open("./results_chosen_num_mutations.txt", "a")
    for i in range(0, num_evolvs):
        print "percent complete: " + str(i / float(num_evolvs))
        init_config = do_lambda(string_len, num_init_config)
        rules_with_fitness = []
        for id_fit, rule in enumerate(rules):
            fitness_arr = []
            for idx, pop in enumerate(init_config):
                init_density_eval = 0
                if pop.count(1) / float(len(pop)) > 0.5:
                    init_density_eval = 1
                for i in range(0, num_generations):
                    new_pop = apply_ca(pop, rule, radius)
                    if check_equal(new_pop, pop):
                        break
                    pop = new_pop
                indiv_fit = calc_fitness_with_density(pop, init_density_eval)
                fitness_arr.append(indiv_fit)
            avg_fit = avg_fitness(fitness_arr)
            rules_with_fitness.append({
                'fitness': avg_fit,
                "rule": rule
            })
        write_to_file(rules_with_fitness)
        rules = evolve_rules(rules_with_fitness)

    f.write("]")
    f.close()


def check_equal(arr1, arr2):
    for i in range(0, len(arr1)):
        if arr1[i] != arr2[i]:
            return False
    return True


def write_to_file(to_write):
    f = open(
        "./results_chosen_num_mutations.txt",
        "a")
    f.write(str(to_write) + ",")
    f.close()


def apply_ca(pop, rule, radius):
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
            new_pop.append(rule[int(bit_string, 2)])
        except IndexError:
            print "index error on "
            print bit_string
            print "rule is: "
            print str(rule)
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


def evolve_rules(rules_with_fitness):
    evolved = []
    rules_with_fitness.sort(key=operator.itemgetter('fitness'), reverse=True)
    perc = int(len(rules_with_fitness) * 0.2)
    print rules_with_fitness
    for i in range(0, perc):
        evolved.append(rules_with_fitness[i].get('rule'))
        rules_with_fitness.pop()
    for i in range(0, len(rules_with_fitness) - 1, 2):

            indiv = rules_with_fitness[i]['rule']
            pivot = random.randint(0, len(indiv))
            first_split = [indiv[:pivot], indiv[pivot:]]

            indiv2 = rules_with_fitness[i + 1]['rule']
            second_split = [indiv2[:pivot], indiv2[pivot:]]

            c1 = copy.copy(first_split[0])
            c2 = copy.copy(second_split[1])
            c1.extend(c2)
            second_split[1].extend(first_split[0])

            evolved.append(mutate(c1))
            evolved.append(mutate(second_split[1]))
            i = i + 1
    return evolved


def mutate(arr):  # we mutate two bits in each of them
    arr = single_mutate(arr)
    arr = single_mutate(arr)
    not_new = True
    num_overlaps = 0
    while not_new and num_overlaps < 3:
        if arr in fitness_evolved:
            arr = single_mutate(arr)
            num_overlaps = num_overlaps + 1
        else:
            not_new = False
            fitness_evolved.append(arr)

    return arr


def single_mutate(arr):

    index = random.randint(0, len(arr) - 1)
    arr[index] = int(not(arr[index]))
    return arr


def avg_fitness(fitness):

    tot = 0
    for i in fitness:
        tot += i
    val = tot / float(len(fitness))
    return val


def calc_fitness_with_density(pop, init_density_eval):

    for i in pop:
        if i != init_density_eval:
            return 0.0
    return 1.0


def do_lambda(size_of_array, num_arrays_needed):

    arrays = []
    num_to_add = size_of_array / float(num_arrays_needed)
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

evolv = input("How many times would you like to evolve? ")
my_ca(2, 121, evolv)
