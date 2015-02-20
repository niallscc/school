Readme for Machine Learning P1 
ID3 Algorithm
Nialls Chavez


Running the Program


        To run the program it requires you to have training and validation files in the same directory that the jar is in. So to run the program once you have the files in the same place you run it with the following command: 


        java -jar ID3Algoithm.jar
 
From this point it will prompt the user for multiple pieces of information: 


* First: it will require the name of the training file.
* Second: It requires the name of the validation file.
* Third: You select the impurity type you want the program to use
* Fourth: You select the threshold you want the program to run with. 


Exceptions. The program will break under the following parameters: 


* The validation or training files do not exist or are entered incorrectly: in this case, the program crashes un-gracefully. 
* The impurity type is incorrectly input: In this case if you do not choose 1 or 2 it will crash. 
* The threshold is in correctly input: in this case if you do not choose 1,2, or 3 the program will crash. 
 
Other information: 


        Included Is also the un-obfuscated java code. This can also be run with the same parameters as the jar from eclipse. This also requires the files to be in the same place as the java project and will run into the same issues as described above. 


Have fun!