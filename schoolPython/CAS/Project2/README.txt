README.txt

This file is the outline for how to run our Project 2 for CS529 it includes
a list of files submitted and a short summary

Root level files (./)

These are the core files used for our program. These should be the ones that
you primarily look at to grade 

GACA_choose_mutations.py

    This one is the file that you (the ta or professor) will run to see 
    if we really did the project it prompts for the number of times you want to mutate
    the rule, We tested with 2 and it took ~10mins on my macbook pro 

GACA_GKL_book.py

    this was for running the GKL rule on the especially fit rule given in the book
    it does pretty well 
    runs really fast

GACA_our_best.py

    this was for running our best rule ( its fitness was .98) (3 neighbors) it goes fast
    too! amazing what happens when you dont have to do all that other stuff

GACA_GKL_space_time.py
    
    This was for generating out cool black/white/gray space time graph. Its pretty awesome
    does it for all the rules writes a huge file! watch out

GACA_improved.py

    This is for using our our improvements to our rule it actually did pretty good 
    ran in approx 2hrs

GACA_mutational_robustness.py

    If you want to run this one you can it takes a bit, and I just physically modified a 
    bunch of them and dropped them on different servers to get it to run all of our 
    mutations. Basically we got a fit rule (.94) and mutated 1 then 2 then 3, .. to 5 bits 
    and saw how robust the rule was

GACA_original.py

    This was the original that generated our graphs of 2 and 3 radius GACA awesomeness

Matlab Code (./matlab_code)

    These files are all pretty standard they really just make figures! nothing much to say!


Results (./results/)

This folder contains a lot of stuff! like an insane amt of stuff it has csv and json files for
all of our runs If you want to see the output of any of the above files check this folder it also
has the python files for making graphs and doing coversions. Some of The files I will describe here
but not all of them because there are too many.

dataGrapher.py

    This file will graph the data for our results_2_neighbors.txt, results_3_neighbors.txt and the results_improved.txt files

dataGrapherBar.py
    this file will do a bargraph for our mutational_robustness files

Misc files
    There are again, a bunch more files in here most of them are just output
    they are all named well so it should be easy to determine which one is which if you want to see the output
    if you have any questions email: niallsc@gmail.com

    Oh also the programs do not output real valid json :( I know thats my bad so what I do is I take the file generated
    replace all the single quotes with double quotes and add a closing bracket at the end
    seems silly but it was way easier and i was tired. 


Testing Files (./testing/)

You shouldnt really need to use these files but they are really just there to
see if things like getting the radius works and so on 

CA.py

    this one was made from the book dont use

CA_old.py

    this one used numpy and made things slow
    dont use

ga_runner.py

    this was made to run the robby the robot thing

GA.py

    also used for robby the robot trash thing

indexTester.py

    This one is used to test if we can get the correct radius

lambda.py

    this was for testing to see if we could get a good distribution
    for our init configs and rules

testEqual.py

    this was used to test to see if we could check equivalency of sets


