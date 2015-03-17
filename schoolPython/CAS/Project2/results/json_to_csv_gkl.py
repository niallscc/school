#!usr/bin/python
import json


def convert():
    opener = open("./results_gkl_book.csv", "w")
    with open("results_gkl_book.txt") as json_file:
        json_data = json.load(json_file)
        gen = 1
        for data_pt in json_data:

            for data in data_pt:
                print data
                opener.write(
                    str(gen) + "," + str(data['init_densities']) + "\n" +
                    str(gen) + "," + str(data['fitnesses']) + "\n" +
                    "\n")
                gen = gen + 1
convert()
