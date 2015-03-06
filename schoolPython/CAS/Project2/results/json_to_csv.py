#!usr/bin/python
import json
import operator


def convert():
    opener = open("./results_gkl_book.csv", "a")
    with open("results_gkl_book.txt") as json_file:
        json_data = json.load(json_file)
        gen = 1
        for data_pt in json_data:
            data_pt.sort(key=operator.itemgetter('fitness'), reverse=True)
            for data in data_pt:
                opener.write(
                    str(gen) + "," +
                    str(data['fitness']) + "," +
                    str(data['rule']) + "\n")
            gen = gen + 1
convert()
