#!usr/bin/python
import json


def convert():
    opener = open("./results_space_time27.csv", "a")
    with open("results_space_time.txt") as json_file:
        json_data = json.load(json_file)
        for i in json_data[27]['populations']:
            opener.write(
                str(json_data[27]['init_densities']) + "," +
                str(json_data[27]['fitness']) + "," +
                str(i) + "\n")

convert()
