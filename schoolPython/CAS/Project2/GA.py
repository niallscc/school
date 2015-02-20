#!usr/bin/python
import operator
import random
import np

# Input to GA:
# 	population of Candidate programs
# 	Fitness Function
#  Output of GA
#   best program as defined by candidate program


def ga(strategies, generations, board, calc_fitness):
    if generations > 0:
        strategies_with_fitness = {}
        counter = 0

        for individual in strategies:
            for iteration in range(0, 100):

                strat_fitness = []
                ind_fitness = 0
                for gene in individual:
                    ind_fitness += calc_fitness(gene, board)
                strat_fitness.append(ind_fitness)
            avg = sum(strat_fitness) / len(strat_fitness)
            data = {'fitness': avg, 'individual': individual}
            strategies_with_fitness['counter'] = data
            counter = counter + 1
        ga(evolve(strategies_with_fitness), generations - 1, board)
    else:
        return(strategies)


def evolve(fitness):

    evolved = []
    sorted_fit = sorted(fitness.items(), key=operator.itemgetter('fitness'))
    for i in range(0, len(sorted_fit)):
        indiv = sorted_fit[i]['individual']
        pivot = random.randint(0, len(indiv))
        first_split = np.split(indiv, pivot)
        indiv2 = []

        if (i + 1) < len(sorted_fit):
            indiv2 = sorted_fit[i + 1]['individual']
        else:
            indiv2 = sorted_fit[i - 1]['individual']

        second_split = np.split(indiv2, pivot)
        evolved.append(mutate(first_split[0]), mutate(second_split[1]))
        evolved.append(mutate(first_split[0]), mutate(second_split[1]))

    return evolved


def mutate(arr):
    mutation_percentage = 50

    if random.randint(0, 100) > mutation_percentage:
        arr[random.randint(0, len(arr))] = random.randint(0, 6)
    return arr
