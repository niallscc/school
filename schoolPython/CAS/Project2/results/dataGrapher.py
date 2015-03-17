#!usr/bin/python
import json
import operator
import matplotlib.pyplot as plt

with open("results_improved.txt") as json_file:
    json_data = json.load(json_file)
    just_fitness = []
    for data_pt in json_data:
        data_pt.sort(key=operator.itemgetter('fitness'), reverse=True)

        just_fitness.append(data_pt[0]['fitness'])
        print "\n"
    plt.plot(just_fitness)
    plt.ylabel("fitness")
    plt.xlabel("generations")
    plt.ylim((0, 1))
    plt.xlim((0, 50))
    plt.show()
