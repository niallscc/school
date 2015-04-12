README.txt

Project 3 by nialls Chavez
UNM CS 2015 Spring
Machine Learning

This project is an exercise in using Logistic Regression and Gradient 
Descent to classify music into different genres. It is a supervised algorithm
and is implemented in three different ways, each of which can be run
from the terminal without any command line input. To my memory I didnt
think that we had to provide any into the system.

Each of these algorithms uses 10 fold cross validation to generate a confusion matrix
of values that we can use to evaluate how well the algorithm performed. 

Normal (project3.py)

Standard Logistic regression with gradient descent
It takes forever to run (not really forever but a while) it could be sped up a lot

Optimized (project3_optimized.py)

This algorithm prioritizes the values in each column that are further away from the standard deviation
than others

MFCC (Project3_mfcc.py)

uses the MFCC algorithm, so this one runs way fast!!!