#!usr/bin/python
from enum import Enum
import scipy as sc
import numpy as np


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
    random = 3


# Enumeration
# Provides a way to get the probabilities associated with diffeent types of wind
# This is primarily used in the Wind object
class WindSpeed(Enum):
    low = .20
    medium = .25
    high = .30


# Object
# This object holds all the necessary information concerning the terrain in a cell
# this data is propogated through the board so each cell of the board has a terrain
# object in it
class Terrain:

    isBurining = False
    fuel = 3
    prob = .4

    def __init__(self, fuel=3, prob=0.4):
        self.prob = prob
        self.fuel = fuel

    def is_burning(self):
        return self.isBurning

    def ignite(self):
        if self.fuel > 0:
            self.fuel -= 1
            self.isBurining = True
        else:
            self.isBurning = False

    def get_prob(self):
        return self. prob

    def burn(self):
        if self.fuel > 0:
            self.fuel -= 1


# Enum
# This object is how I actually put the different types of terrain into each cell
# this makes so that to change how a terrain type behaves all i have to do is change the data
# in the enum! pretty nifty!
class TerrainType(Enum):

    Tree = Terrain(5, 0.4)
    Grass = Terrain(3, 0.6)
    Road = Terrain(0, 0)


# Object
# This object defines how and what wind does. So wind gets initialized with a direction and speed
# then we can modify the propbability of a cell catchinf fire based on the wind
class Wind:
    direction = Direction.east
    speed = WindSpeed.low
    can_change = False
    prob_speed_change = 0.2
    prob_dir_change = 0.1

    def __init__(self, direction=Direction.west, speed=WindSpeed.low, can_change=False, speed_change=0.2, dir_change=.1):
        self.direction = direction
        self.speed = speed
        self.can_change = can_change
        self.prob_speed_change = speed_change
        self.prob_dir_change = dir_change

    def get_direction(self):
        return self.drection

    def get_speed(self):
        return self.speed

    def change(self, prob_change_dir):
        if self.can_change:
            if will_happen(self.prob_speed_change):
                self.direction = WindSpeed[sc.random.randint(0, 3)]
            if will_happen(self.prob_dir_change):
                self.direction = Direction[sc.random.randint(0, 4)]


# Object
# This is the big one, This object defines the entire map or board we are working with.
# It is called by our "Controller" ( term used loosely ) to define what is going on at a given time
# interval
class Board:

    board_width = 19
    board_height = 19
    board_type = BoardType.forest
    has_roads = True
    board = []

    def __init__(self, width=19, height=19, configuration=BoardType.forest, wind=Wind(), has_roads=True):
        self.board_width = width
        self.board_height = height
        self.board_type = configuration
        self.has_roads = has_roads
        self.build_board()

    def start_fire(self, num_fires):
        for i in range(0, num_fires):
            self.get_cell(
                sc.random.randint(0, self.board_width),
                sc.random.randint(0, self.board_height)
            ).ignite()

    def visualize_board(self):
        # for i in range(0, 100):
        for row in self.board:
            to_print = ""
            for cell in row:
                if cell == 0:
                    to_print += "+"
                elif cell == 1:
                    to_print += "."
                elif cell == 2:
                    to_print += "="
                elif cell == 3:
                    to_print += "*"
                elif cell == 4:
                    to_print += "_"
            print to_print
        print "\n"

    def build_road(self, direction=Direction.north):
        return 0

    # This gets neighbors in a box around a given cell
    # Returns: Array of tuples
    def get_neighbors(self, x, y):
        return [
            (x2, y2) for x2 in range(x - 1, x + 2) for y2 in range(y - 1, y + 2) if (-1 < x <= self.board_width and -1 < y <= self.board_height and (x != x2 or y != y2) and(0 <= x2 <= self.board_width) and (0 <= y2 <= self.board_height))
        ]

    def get_terrain_in_direction(self, neighbors, direction):

        if direction == Direction.north:
            if (self.location[0] - 1) >= 0:
                return ((self.location[0] - 1), self.location[1])
        elif direction == Direction.south:
            if (self.location[0] + 1) <= self.board_height:
                return ((self.location[0] + 1), self.location[1])
        elif direction == Direction.east:
            if (self.location[1] + 1) <= self.board_width:
                return (self.location[0], (self.location[1] + 1))
        elif direction == Direction.west:
            if (self.location[1] - 1) >= 0:
                return (self.location[0], (self.location[1] - 1))
        else:
            return (0, 0)


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


def run():
    return 0


def get_input():
    return 0


run()
