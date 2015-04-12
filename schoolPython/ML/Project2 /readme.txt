README For Nialls Chavez's Project 2

This Folder contains all the files for Project 2 for 
Machine Learning CS529 Spring 2015. 

The project files are all written in python and were compiled
using python 2.6 I have not tried the files on the CS machines
in Farris so I do not know if there are any conflicts between the 
versions. I am assuming not, but we all know what happens when you assume
right? Anyway, here are the file breakdowns: 

projec2.py

this is the vanilla version of Naive Bayes I have implemented
pretty straight forward. runs in about 30 seconds.

projet2_ranked.py 

This is for Question 6 which asks us to rank the words we find and
come up with a good way of modifying our probabilities using this information

project2_retrained.py

This file takes a while to run, it runs 100 times for different beta values and
produces the information that is contained in accuracy_vals.txt I really should have
written these values to a file but I didnt have time. 

val_printer.py

This file makes the logscale graph for the values contained in accuracy_vals.txt
super straight forward run it and itll make a graph. 