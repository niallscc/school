#!usr/bin/python
# from sklearn import datasets
# from sklearn.naive_bayes import GaussianNB
import math

print "Nialls Chavez Project 2 Machine Learning"


class NaiveBayes:

    training_object = None
    testing_object = None
    priors = []
    training_data_name = "./data/train.data"
    training_label_name = "./data/train.label"
    test_data_name = "./data/test.data"
    testing_label_name = "./data/test.label"
    vocab_file_name = "./data/vocabulary.txt"

    def __init__(self):
        # self.test_with_sk_learn()
        self.read_data_files()
        self.calc_priors()
        self.do_bayes()

    def read_data_files(self):
        # Order in obj is: Word id, Word
        # vocab = self.read_file(self.vocab_file_name, False)
        # print "Vocabulary loaded. file: %s num rows: %i" % (
        #    self.vocab_file_name, len(vocab))

        # Order in obj is: Document ID, Word Id, count
        training_data = self.read_file(self.training_data_name, True)
        print "training data loaded. file: %s num rows: %i" % (
            self.training_data_name, len(training_data))

        # Order in obj is: Word id, News Group Label ID
        training_label = self.read_file(self.training_label_name, True)
        print "training labels loaded. file: %s num rows: %i" % (
            self.training_label_name, len(training_label))

        # Order in obj is: Document ID, Word Id, count
        testing_data = self.read_file(self.test_data_name, True)
        print "testing data loaded. file: %s num rows: %i" % (
            self.test_data_name, len(testing_data))

        # Order in obj is: Word id, News Group Label ID
        testing_label = self.read_file(self.testing_label_name, True)
        print "testing label loaded. file: %s num rows: %i" % (
            self.testing_label_name, len(testing_label))

        self.training_object = self.DataObject(
            training_data, training_label)
        self.testing_object = self.parse_test_data(
            testing_data, testing_label)

    def read_file(self, filename, are_ints):
        data = []
        with open(filename) as f:
            content = f.readlines()
            for line in content:
                splitted = line.split()
                if are_ints is True:
                    splitted = [
                        int(numeric_string)for numeric_string in splitted
                    ]
                if len(splitted) == 1:
                    data.append(splitted[0])
                else:
                    data.append(splitted)
        return data

    def calc_priors(self):

        just_data = self.training_object.get_all_totals()
        tot = sum(map(sum, just_data))
        for label in just_data:
            self.priors.append(sum(label) * 1.0 / tot * 1.0)

    def test_with_sk_learn(self):

        # iris = datasets.load_iris()

        # print "Data is: " + str(iris.data)
        # print "Target is: " + str(iris.target)

        # gnb = GaussianNB()
        # y_pred = gnb.fit(iris.data, iris.target).predict(iris.data)
        # print("Number of mislabeled points out of a total %d points : %d"
        #      % (iris.data.shape[0], (iris.target != y_pred).sum()))
        import numpy as np
        x = np.random.randint(5, size=(6, 100))
        y = np.array([1, 2, 3, 4, 5, 6])
        print x
        print y
        from sklearn.naive_bayes import MultinomialNB
        clf = MultinomialNB()
        clf.fit(x, y)
        print clf.fit(x, y)
        print clf.get_params()
        MultinomialNB(alpha=1.0, class_prior=None, fit_prior=True)
        print(clf.predict(x[2]))

    def parse_test_data(self, data, label):
        organized_data = []
        i = 0
        for idx, l in enumerate(label):
            length = len(data)
            while i < length and data[i][0] == (idx + 1):
                data[i].append(l)
                organized_data.append(data[i])
                i += 1
        return organized_data

    def do_bayes(self):
        testing_data = self.testing_object
        results = []
        for data in testing_data:
            best, selected_class = self.classify_label(data)
            r = [best, selected_class, data]
            results.append(r)
        print results

    def classify(self, data):
        training_obj = self.training_object.get_data()
        best = -100000
        cur_class = 1
        selected_class = -1
        total = self.training_object.get_totals_per_word(data[1])
        print data
        for idx, class_data in training_obj:
            res = 0
            for article in enumerate(class_data):
                occurs = self.get_occurs(data[2], article)
                res += data[2] * math.log((occurs + 1.0) / (total + 20.0))
            if best < res:
                best = res
                selected_class = cur_class
            cur_class += 1
        # print "word_data: " + str(data) + " best_pick: " + str(best) + " label is: " + str(selected_class)
        return best, selected_class

    def classify_label(self, data):

        best = -100000
        cur_class = 1
        selected_class = -1
        total = self.training_object.get_totals_per_word(data[1])
        # print data
        for i in range(0, 20):
            occurs = self.training_object.get_total_per_label(data[1], i)
            # print occurs
            res = data[2] * math.log((occurs + 1.0) / (total + 20.0))
            if best < res:
                best = res
                selected_class = cur_class
            cur_class += 1
        return best, selected_class

    def get_occurs_in_article(self, word_id, article):
        if word_id in article[1]:
            return article[1][word_id]
        else:
            return 0

    class DataObject:
        # each data element looks like:
        # word_id: [document_id, word_id, count, class]
        __data = []
        __total_word_per_label = []
        # These are the means and stdevs for
        # each of the different labels based on
        # the count of words found
        # __means = []
        # __stdevs = []

        def __init__(self, data, label):
            self.__data = self.build(label, data)

        def build(self, label, data):
            separated_data = []
            i = 0
            cur_label = 1
            temp_container = []
            total_per_word = {}
            for idx, l in enumerate(label):
                temp = {}
                length = len(data)
                while i < length and data[i][0] == (idx + 1):
                    c = data[i]
                    c1 = c[1]
                    c2 = c[2]
                    if c1 in total_per_word:
                        total_per_word[c1] += c2
                    else:
                        total_per_word[c1] = c2
                    temp[c1] = c2
                    i = i + 1
                if l == cur_label:
                    temp_container.append(temp)
                else:
                    separated_data.append(temp_container)
                    temp_container.append(temp)
                    self.__total_word_per_label.append(total_per_word)
                    total_per_word = {}
                    temp_container = []
                    cur_label = l
            self.__total_word_per_label.append(total_per_word)
            separated_data.append(temp_container)
            return separated_data

        def get_totals_per_word(self, word_id):
            count = 0
            for i in self.__total_word_per_label:
                if word_id in i:
                    count += i[word_id]
            return count

        def get_all_totals(self):
            return self.__total_word_per_label

        def get_total_per_label(self, word_id, label):

            if(word_id in self.__total_word_per_label[(label - 1)]):
                return self.__total_word_per_label[(label - 1)][word_id]
            else:
                return 0

        def get_data(self):
            return self.__data
        # def stdev(self, counts):
        #    avg = self.mean(counts)
        #    variance = sum([
        #        pow(x - avg, 2) for x in counts]) / float(len(counts) - 1)
        #    return math.sqrt(variance)
        # def get_mean(self, label_id):
        #    return self.__means[label_id]
        # def get_stdevs(self, label_id):
        #    return self.__stdevs(label_id)


NaiveBayes()
