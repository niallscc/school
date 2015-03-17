#!usr/bin/python
import json
import operator
import matplotlib.pyplot as plt

fitness_avg = []

for i in range(1, 6):
    with open("results_mutational_robustness_" + str(i) + ".txt") as json_file:
        json_data = json.load(json_file)
        just_fitness = []
        for data_pt in json_data:
            data_pt.sort(key=operator.itemgetter('fitness'), reverse=True)
            just_fitness.append(data_pt[0]['fitness'])
        fitness_avg.append((sum(just_fitness) / len(just_fitness)))
        # fitness_avg.append(data_pt[0]['fitness'])

print fitness_avg
N = len(fitness_avg)

x = range(N)
print x

rects1 = plt.bar(x, fitness_avg, 0.5, alpha=0.4, color='b')
plt.title('Average Fitness per mutation')
plt.ylabel("fitness")
plt.xlabel("mutations away")
plt.xticks(x, ('1 mutations', '2 mutations', '3 mutations', '4 mutations', '5 mutations'))
plt.legend()
plt.tight_layout()
plt.show()
