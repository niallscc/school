#!usr/bin/python

print "Nialls Chavez Project 2 Machine Learning"


class NaiveBayes:

    training_data = []
    training_label = []
    testing_label = []
    testing_data = []

    vocab = []

    training_data_name = "./data/train.data"
    training_label_name = "./data/train.label"
    test_data_name = "./data/test.data"
    testing_label_name = "./data/test.label"
    vocab_file_name = "./data/vocabulary.txt"

    def __init__(self):

        self.read_data_files()

    def read_data_files(self):
        self.training_data = self.read_file(self.training_data_name)
        print "training data loaded. file: %s num rows: %i" % (
            self.training_data_name, len(self.training_data))

        self.training_label = self.read_file(self.training_label_name)
        print "training labels loaded. file: %s num rows: %i" % (
            self.training_label_name, len(self.training_label))

        self.testing_data = self.read_file(self.test_data_name)
        print "testing data loaded. file: %s num rows: %i" % (
            self.test_data_name, len(self.testing_data))

        self.testing_label = self.read_file(self.testing_label_name)
        print "testing label loaded. file: %s num rows: %i" % (
            self.testing_label_name, len(self.testing_label))

        self.vocab = self.read_file(self.vocab_file_name)
        print "Vocabulary loaded. file: %s num rows: %i" % (
            self.vocab_file_name, len(self.vocab))

    def read_file(self, filename):
        data = []
        with open(filename) as f:
            content = f.readlines()
            for line in content:
                data.append(line.split())
        return data


NaiveBayes()
