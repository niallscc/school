#!usr/bin/python
import math
import numpy as np
# from pprint import pprint
# import numpy as np
print "Nialls Chavez Project 2 Machine Learning"


class NaiveBayes:
    # This is the training object it is an actual object and
    # holds a bunch of cool stuff on the training data. The
    # acutal object is described below
    training_object = None
    # This is the testing data, that we are trying to
    # classify against the trained data
    # represented as:
    #   array article_id:
    #       [article_id, word_id, count, label]
    testing_object = None
    # Just a list of the possible vocabulary that could be
    # found in the testing and training data files
    # array:
    #   word_id: word
    vocab_list = None
    # These are the Priors, represented as:
    # Label id: () #articles/ total_articles)

    priors = []

    # empty priors gives (alpha - 1)/total_word_in_label + ((alpha - 1) * length_vocab)
    empty_map_totals = []
    # This is the MAP for each word. The Array is represented as:
    # Label_id (0-19):
    #   word_id: MAP(word_id)
    all_maps = []

    def __init__(self):
        # self.test_with_sk_learn()
        self.read_data_files()
        print "Calculating MLE..."
        self.calc_mle()
        print "MLE Calculated."
        print "Calculating MAP..."
        self.calc_map()
        print "MAP calculated."
        print "Classifying..."
        acc = self.classify()
        print "Classified."
        print "accuracy was: " + str(acc)

    # Reads all the data in from the different files and builds the different objects
    def read_data_files(self):
        training_data_name = "./data/train.data"
        training_label_name = "./data/train.label"
        test_data_name = "./data/test.data"
        testing_label_name = "./data/test.label"
        vocab_file_name = "./data/vocabulary.txt"
        # Order in obj is: Word id, Word
        self.vocab_list = self.read_file(vocab_file_name, False)
        print "Vocabulary loaded. file: %s num rows: %i" % (vocab_file_name, len(self.vocab_list))

        # Order in obj is: Document ID, Word Id, count
        training_data = self.read_file(training_data_name, True)
        print "training data loaded. file: %s num rows: %i" % (training_data_name, len(training_data))

        # Order in obj is: Word id, News Group Label ID
        training_label = self.read_file(training_label_name, True)
        print "training labels loaded. file: %s num rows: %i" % (training_label_name, len(training_label))

        # Order in obj is: Document ID, Word Id, count
        testing_data = self.read_file(test_data_name, True)
        print "testing data loaded. file: %s num rows: %i" % (test_data_name, len(testing_data))

        # Order in obj is: Word id, News Group Label ID
        testing_label = self.read_file(testing_label_name, True)
        print "testing label loaded. file: %s num rows: %i" % (testing_label_name, len(testing_label))

        self.training_object = self.DataObject(
            training_data, training_label)
        self.testing_object = self.parse_test_data(
            testing_data, testing_label)

    # just does the initial read. Nothing exciting at all
    def read_file(self, filename, are_ints):
        data = []
        with open(filename) as f:
            content = f.readlines()
            for line in content:
                splitted = line.split()
                if are_ints is True:
                    splitted = [
                        int(numeric_string) for numeric_string in splitted
                    ]
                if len(splitted) == 1:
                    data.append(splitted[0])
                else:
                    data.append(splitted)
        return data

    # this builds the actual test data set up As described above it is:
    # array article_id:
    #       [article_id, word_id, count, label]
    def parse_test_data(self, data, label):
        organized_data = []
        i = 0
        for idx, l in enumerate(label):
            length = len(data)
            article = []
            while i < length and data[i][0] == (idx + 1):
                data[i].append(l)
                article.append(data[i])
                i += 1
            organized_data.append(article)
        return organized_data

    # This function calculates the MLE, and populates our priors
    # array.
    # We first get all the data we have count up all the articles
    # There should be 11269
    # then we divide that by the number of articles with a given label

    def calc_mle(self):

        d = self.training_object.get_data()
        tot = 0
        for i in d:
            tot += len(i)
        for i in d:
            self.priors.append((len(i) * 1.0) / tot)

    # This function calculates our Maximum Aposteriori
    # this works by taking the count of each word per label + alpha/
    # total words in the label + (alpha * all total words )

    def calc_map(self):

        for alpha in np.arange(0.00001, 1.0, 0.01):
            map_totals = []
            empty_map = []
            a = 1.0 + alpha
            for label_id, label_words in enumerate(self.training_object.get_all_totals()):
                m_a_p = {}
                total_words_in_label = self.training_object.get_num_words_in_label(label_id)
                for word_id in label_words:
                    m_a_p[word_id] = (label_words[word_id] + (a - 1.0)) / (total_words_in_label + ((a - 1.0) * 61188))

                empty_map.append((a - 1.0) / (total_words_in_label + ((a - 1.0) * 61188)))
                map_totals.append(m_a_p)
            self.all_maps.append(map_totals)
            self.empty_map_totals.append(empty_map)
    # This does the actual classification. This is the first time we do anything to the the testing object
    # this classifies by taking the max of log(MLE(label_id)) + SUM(word_i * log(MAP(label, word)))

    def classify(self):
        accuracy_arr = []
        accuracy_per_label = []
        alphas = np.arange(0.00001, 1.0, 0.01)
        for map_index, map_totals in enumerate(self.all_maps):
            # print map_totals
            for i in range(0, 20):
                accuracy_per_label.append([])
            for article in self.testing_object:
                correct_label = article[0][3]
                probs_per_label = []
                for idx, mle_of_label in enumerate(self.priors):
                    res = 0
                    for word_data in article:
                        if word_data[1] in map_totals[idx]:
                            res += word_data[2] * math.log(map_totals[idx][word_data[1]], 2)
                        else:
                            res += word_data[2] * math.log(self.empty_map_totals[map_index][idx], 2)
                    val = math.log(mle_of_label, 2) + res
                    probs_per_label.append(val)
                selected_label = probs_per_label.index(max(probs_per_label)) + 1

                if selected_label == correct_label:
                    accuracy_per_label[correct_label - 1].append(1)
                    accuracy_arr.append(1)
                else:
                    accuracy_arr.append(0)
                    accuracy_per_label[correct_label - 1].append(0)

            accuracy = reduce(lambda x, y: x + y, accuracy_arr) / (len(accuracy_arr) * 1.0)
            print str(alphas[map_index]) + " " + str(accuracy)
        return accuracy

    # ALrighty I am 99.999% sure this stuff is working I checked the counts and everything and it looks good
    # basically I go through each document and build two objects
    # One object with just label[articles[words[word_id, count, label]]]
    # The second is structures like : array[Label[word_id:total count in label]]]
    class DataObject:
        __data = []
        __word_counts_by_label = []

        def __init__(self, data, label):
            self.build(label, data)

        def build(self, label, data):

            organized_data = []
            i = 0
            label_data = []
            cur_label = 1
            word_counts_by_label = []
            word_counts = {}
            for idx, l in enumerate(label):
                length = len(data)
                article = []
                while i < length and data[i][0] == (idx + 1):
                    d = [data[i][1], data[i][2], l]
                    article.append(d)
                    i += 1
                if cur_label == l:
                    label_data.append(article)
                    for word_data in article:
                        if word_data[0] not in word_counts:
                            word_counts[word_data[0]] = word_data[1]
                        else:
                            word_counts[word_data[0]] += word_data[1]
                else:
                    word_counts_by_label.append(word_counts)
                    word_counts = {}
                    for word_data in article:
                        word_counts[word_data[0]] = word_data[1]
                    organized_data.append(label_data)
                    label_data = []
                    label_data.append(article)
                    cur_label = l
            word_counts_by_label.append(word_counts)
            self.__word_counts_by_label = word_counts_by_label
            organized_data.append(label_data)
            self.__data = organized_data

        # These guys are all just helper functions so i dont have to think
        # so much.
        def get_num_words_in_label(self, label_id):
            s = 0
            for i in self.__word_counts_by_label[label_id]:
                s += self.__word_counts_by_label[label_id][i]
            return s

        def get_all_totals(self):
            return self.__word_counts_by_label

        def get_total_by_word(self, word_id, label_id):
            if word_id in self.__word_counts_by_label[label_id]:
                return self.__word_counts_by_label[label_id][word_id]
            else:
                return 0

        def get_data(self):
            return self.__data


NaiveBayes()
