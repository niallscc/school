#!usr/bin/python
import json
import numpy as np
import matplotlib.pyplot as plt

with open("results.txt") as json_file:
    json_data = json.load(json_file)
    just_fitness = []
    for data_pt in json_data:
        fitness = 0.0
        for data in data_pt:
            print data
            fitness = fitness + data['fitness']
        avg = float(fitness) / 10.0
        print avg
        plt.plot(avg)
        print "\n"

    plt.show()
