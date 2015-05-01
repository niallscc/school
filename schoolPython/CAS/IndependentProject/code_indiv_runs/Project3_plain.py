#!usr/bin/python
from enum import Enum
import scipy as sc
import numpy as np
import sys
import csv
import matplotlib
matplotlib.use('TKAgg')
from matplotlib import pyplot as plt
from matplotlib import animation
import copy


wind_dir_change_arr = []
wind_speed_change_arr = []
iterate_counter = 0


# Enumeration
# Provides an easy way to access the different directions on the board
# Primarily used for wind
class Direction(Enum):
    north = 0
    east = 1
    south = 2
    west = 3


# Enumeration
# This enum provides the outline for how to do differnt types os boards
# the actual creation of the board is handled in the Board object
class BoardType(Enum):
    forest = 0
    plain = 1
    combo = 2
    # random = 3


# Enumeration
# Provides a way to get the probabilities associated with diffeent types of wind
# This is primarily used in the Wind object
class WindSpeed(Enum):
    none = 0.0
    low = .05
    medium = .1
    high = .2


# Object
# This object holds all the necessary information concerning the terrain in a cell
# this data is propogated through the board so each cell of the board has a terrain
# object in it
class Terrain:

    isBurning = False
    fuel = 3
    prob = .4
    viz = "+"
    can_burn = True

    def __init__(self, fuel=3, prob=0.4, viz="+", can_burn=True):
        self.prob = prob
        self.fuel = fuel
        self.viz = viz
        self.can_burn = can_burn

    def ignite(self):
        if self.can_burn:
            if self.fuel > 0:
                self.isBurning = True
                self.viz = "*"
            else:
                self.isBurning = False

    def burn(self):
        if self.can_burn and self.fuel > 0:
            self.fuel -= 1
            if self.fuel == 0:
                self.viz = "_"
                self.can_burn = False
                self.isBurning = False


# Enum
# This object is how I actually put the different types of terrain into each cell
# this makes so that to change how a terrain type behaves all i have to do is change the data
# in the enum! pretty nifty!
class TerrainType(Enum):

    Tree = Terrain(5, 0.05, "+")
    Grass = Terrain(3, 0.10, ".")
    Road = Terrain(0, 0, "=")


# Object
# This object defines how and what wind does. So wind gets initialized with a direction and speed
# then we can modify the propbability of a cell catchinf fire based on the wind
class Wind:
    direction = Direction.east
    speed = WindSpeed.low
    can_change = False
    prob_speed_change = 0.05
    prob_dir_change = 0.025
    WindSpeeds = [WindSpeed.none, WindSpeed.low, WindSpeed.medium, WindSpeed.high]
    directions = [Direction.north, Direction.east, Direction.west, Direction.south]

    def __init__(self, direction=Direction.north, speed=WindSpeed.high, can_change=True, speed_change=0.05, dir_change=.025):
        self.direction = direction
        self.speed = speed
        self.can_change = can_change
        self.prob_speed_change = speed_change
        self.prob_dir_change = dir_change

    def change(self, iteration):
        global wind_dir_change_arr
        global wind_speed_change_arr
        if self.can_change:
            if will_happen(self.prob_speed_change):
                self.speed = self.WindSpeeds[sc.random.randint(0, 4)]
                wind_speed_change_arr.append([iteration, self.speed])
            if will_happen(self.prob_dir_change):
                self.direction = self.directions[sc.random.randint(0, 4)]
                wind_dir_change_arr.append([iteration, self.direction])


# Object
# This is the big one, This object defines the entire map or board we are working with.
# It is called by our "Controller" ( term used loosely ) to define what is going on at a given time
# interval
class Board:

    board_width = 20
    board_height = 20
    board_type = BoardType.forest
    has_roads = True
    board = []
    wind = Wind()

    def __init__(self, width=40, height=40, configuration=BoardType.plain, wind=Wind(), has_roads=False):
        self.board_width = width
        self.board_height = height
        self.board_type = configuration
        self.has_roads = has_roads
        self.wind = wind
        print self.board_width

    def start_fire(self, num_fires):
        for i in range(0, num_fires):
            y = sc.random.randint(0, self.board_height)
            x = sc.random.randint(0, self.board_width)
            lame(str(y) + " " + str(x), "Igniting Cell")
            self.board[y][x].ignite()

    def build_road(self, direction=Direction.north):
        if direction == Direction.north or direction == Direction.south:
            starting_cell = sc.random.randint(1, self.board_width)
            for i in range(0, self.board_height):
                self.board[starting_cell][i] = TerrainType.Road
        elif direction == Direction.east or direction == Direction.west:
            starting_cell = sc.random.randint(1, self.height)
            for i in range(0, self.board_height):
                self.board[i][starting_cell] = TerrainType.Road

    # This gets neighbors in a box around a given cell
    # Returns: Array of tuples
    def get_new_burning_neighbors(self, y, x):
        indexes = [(y - 1, x - 1), (y - 1, x), (y - 1, x + 1), (y, x - 1), (y, x + 1), (y + 1, x - 1), (y + 1, x), (y + 1, x + 1)]
        ret_arr = []
        for idx, n in enumerate(indexes):
            if n[0] < self.board_height and n[0] >= 0 and n[1] >= 0 and n[1] < self.board_height:
                p = self.board[n[0]][n[1]].prob
                if self.wind.direction == Direction.north:
                    if n[0] == (y - 1):
                        p += self.wind.speed
                    elif n[0] == (y + 1):
                        p -= (self.wind.speed / 2.0)
                elif self.wind.direction == Direction.south:
                    if n[0] == (y + 1):
                        p += self.wind.speed
                    elif n[0] == (y - 1):
                        p -= (self.wind.speed / 2.0)
                elif self.wind.direction == Direction.east:
                    if n[1] == (x + 1):
                        p += self.wind.speed
                    elif n[1] == (x - 1):
                        p -= (self.wind.speed / 2.0)
                elif self.wind.direction == Direction.west:
                    if n[1] == (x - 1):
                        p += self.wind.speed
                    elif n[1] == (x + 1):
                        p -= (self.wind.speed / 2.0)

                if will_happen(p):
                    ret_arr.append(self.board[n[0]][n[1]])

        if self.wind.speed is not WindSpeed.none:
            if self.wind.direction == Direction.north and (y - 2 >= 0):
                if will_happen(self.wind.speed):
                    ret_arr.append(self.board[y - 2][x])
            elif self.wind.direction == Direction.south and (y + 2 < self.board_height):
                if will_happen(self.wind.speed):
                    ret_arr.append(self.board[y + 2][x])
            elif self.wind.direction == Direction.east and (x + 2 < self.board_width):
                if will_happen(self.wind.speed):
                    ret_arr.append(self.board[y][x + 2])
            elif self.wind.direction == Direction.east and (x - 2 >= 0):
                if will_happen(self.wind.speed):
                    ret_arr.append(self.board[y][x - 2])

        # print ret_arr
        return ret_arr

    def build_board(self):
        w = self.board_width
        h = self.board_height
        if self.board_type == BoardType.forest:
            print "forest"
            self.board = [[copy.deepcopy(TerrainType.Tree) for j in range(0, w)] for i in range(0, h)]
        elif self.board_type == BoardType.plain:
            print "plain"
            self.board = [[copy.deepcopy(TerrainType.Grass) for j in range(0, w)] for i in range(0, h)]
        elif self.board_type == BoardType.combo:
            print "combo"
            self.board = [[copy.deepcopy(TerrainType.Tree) for j in range(0, w)] for i in range(0, h)]
            for i in range(0, int((w * h) / 2)):
                self.board[sc.random.randint(0, w)][sc.random.randint(0, h)] = copy.deepcopy(TerrainType.Grass)
        # else:
        #    for i in range(0, (w * h)):
        #         self.board[sc.random.randint(0, w)][sc.random.randint(0, h)] = copy.deepcopy(TerrainType[sc.random.randint(0, 1)])
        if self.has_roads:
            self.build_road()

        self.start_fire(1)
        return self.board,

    def iterate(self, dunno):
        global iterate_counter
        new_burning_cells = []
        for y in range(0, self.board_height):
            for x in range(0, self.board_width):
                if self.board[y][x].isBurning:
                    new_burning_cells += self.get_new_burning_neighbors(y, x)
                    self.board[y][x].burn()
        for c in new_burning_cells:
            c.ignite()
        self.wind.change(iterate_counter)
        iterate_counter += 1
        return self.board,

    def show(self):
        board_out = ""
        for y in range(0, self.board_height):
            for x in range(0, self.board_width):
                board_out += " " + self.board[y][x].viz
            board_out += "\n"
        print board_out
        return board_out

    def get_board_vals(self):
        tree = []
        fire = []
        grass = []
        burnt = []
        for y in range(0, self.board_height):
            for x in range(0, self.board_width):
                if self.board[y][x].viz is "+":
                    tree.append((y, x))
                elif self.board[y][x].viz is "*":
                    fire.append((y, x))
                elif self.board[y][x].viz is ".":
                    grass.append((y, x))
                else:
                    burnt.append((y, x))

        return tree, fire, grass, burnt


def will_happen(prob):

    r = np.zeros(100, dtype=np.int)
    for i in range(0, int((prob * 100))):
        rand = sc.random.randint(0, 100)
        while r[rand] == 1:
            rand = sc.random.randint(0, 100)
        r[rand] = 1

    if r[sc.random.randint(0, 100)] == 1:
        return True
    else:
        return False


def lame(var, tag="NONE"):
    if(isinstance(var, list) or type(var).__module__ == np.__name__):
        print "[" + str(tag) + "](" + str(len(var)) + ") : " + str(var)
    else:
        print "[" + str(tag) + "]: " + str(var)
    return var


def get_input():
    print len(sys.argv)


def run_for_save():
    wind_arr = [
        Wind(Direction.north, WindSpeed.none),
        Wind(Direction.north, WindSpeed.low),
        Wind(Direction.north, WindSpeed.medium),
        Wind(Direction.north, WindSpeed.high)
    ]
    wind_names = ["none", "low", "medium", "high"]
    board_type_arr = [BoardType.plain, BoardType.combo]
    board_names = ["plain", "combo"]
    for b_index, b_type in enumerate(board_type_arr):
        for w_index, w_type in enumerate(wind_arr):
            board = Board(40, 40, b_type, w_type)
            # save_data = []
            with open(board_names[b_index] + "_" + wind_names[w_index] + '.csv', 'wb') as csvfile:
                data_writer = csv.writer(csvfile, delimiter=',', quotechar='|', quoting=csv.QUOTE_MINIMAL)
                for i in range(0, 200):
                    board.build_board()
                    new_board = board.show()
                    i = 0
                    while i < 1000 and "*" in new_board:
                        board.iterate(0)
                        new_board = board.show()
                        i += 1
                    data_writer.writerow((i, new_board.count('_')))


def run_multi_type():
    wind_arr = [
        Wind(Direction.north, WindSpeed.none),
        Wind(Direction.north, WindSpeed.low),
        Wind(Direction.north, WindSpeed.medium),
        Wind(Direction.north, WindSpeed.high)
    ]
    wind_names = ["none", "low", "medium", "high"]
    board_type_arr = [BoardType.plain, BoardType.combo, BoardType.forest]
    board_names = ["plain", "combo", "forest"]
    for b_index, b_type in enumerate(board_type_arr):
        for w_index, w_type in enumerate(wind_arr):
            board = Board(40, 40, b_type, w_type)
            # save_data = []
            with open(board_names[b_index] + "_" + wind_names[w_index] + '.csv', 'wb') as csvfile:
                data_writer = csv.writer(csvfile, delimiter=',', quotechar='|', quoting=csv.QUOTE_MINIMAL)
                for i in range(0, 200):
                    board.build_board()
                    new_board = board.show()
                    i = 0
                    while i < 1000 and "*" in new_board:
                        board.iterate(0)
                        new_board = board.show()
                        i += 1
                    data_writer.writerow((i, new_board.count('_')))


def run_for_save_indv():
    w = Wind(Direction.north, WindSpeed.high)
    wind_name = "medium"
    board_type = BoardType.plain
    board_name = "plain"
    board = Board(40, 40, board_type, w)
    with open(board_name + "_" + wind_name + '.csv', 'wb') as csvfile:
        data_writer = csv.writer(csvfile, delimiter=',', quotechar='|', quoting=csv.QUOTE_MINIMAL)
        for i in range(0, 200):
            board.build_board()
            new_board = board.show()
            i = 0
            while i < 1000 and "*" in new_board:
                board.iterate(0)
                new_board = board.show()
                i += 1
            data_writer.writerow((i, new_board.count('_')))


def run():

    # inp = get_input()
    board = Board()
    do_save = False
    if do_save:
        board.build_board()
        fig, ax = plt.subplots()

        # tree, = ax.plot([], [], "g^")
        # grass, = ax.plot([], [], "yo")
        # burnt, = ax.plot([], [], "b--")
        # fire, = ax.plot([], [], "ro")

        def init():
            board = Board()
            board.build_board()
            tree, fire, grass, burnt = board.get_board_vals()
            # tree_x = [(1,2), (2,3), (3,4), (4, 5)]
            # tree_y = [1, 2, 3, 4, 5]
            # tree.set_data(tree_x, tree_y)
            # t = np.arange(0., 5.,  sprint t0.2)
            #
            # red dashes, blue squares and green triangles
            # print tree
            board.show()
            for i in tree:
                plt.plot(i[0], i[1], 'g^')
            for i in grass:
                plt.plot(i[0], i[1], 'y.')
            for i in burnt:
                plt.plot(i[0], i[1], 'b_')
            for i in fire:
                plt.plot(i[0], i[1], 'r*')

        def animate(i):
            board.iterate(0)
            board.show()
            tree, fire, grass, burnt = board.get_board_vals()
            plt.clf()
            for i in tree:
                plt.plot(i[0], i[1], 'g^')
            for i in grass:
                plt.plot(i[0], i[1], 'y.')
            for i in burnt:
                plt.plot(i[0], i[1], 'b_')
            for i in fire:
                plt.plot(i[0], i[1], 'r*')
        anim = animation.FuncAnimation(fig, animate, np.arange(1, 150), init_func=init, interval=25, blit=True)
        anim.save('animation.mp4', fps=20)

    else:
        board.build_board()
        new_board = board.show()
        i = 0
        while i < 1000 and "*" in new_board:
            board.iterate(0)
            new_board = board.show()
            i += 1
        global wind_speed_change_arr
        global wind_dir_change_arr
        burned_cells = new_board.count('_')
        print "Wind Speed Change: " + str(wind_speed_change_arr)
        print "Wind Dir Change: " + str(wind_dir_change_arr)
        print "Number of Burned Cells: " + str(burned_cells)

run()
# run_for_save_indv()
# run_for_save()
# run_multi_type()
