#!usr/bin/python
from enum import Enum
import scipy as sc
import numpy as np
import sys
import matplotlib
matplotlib.use('TKAgg')
from matplotlib import pyplot as plt
from matplotlib import animation
import copy


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
    none = 0.0
    low = .20
    medium = .25
    high = .30


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

    Tree = Terrain(5, 0.07, "+")
    Grass = Terrain(3, 0.1, ".")
    Road = Terrain(0, 0, "=")


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

    def change(self):
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

    board_width = 20
    board_height = 20
    board_type = BoardType.forest
    has_roads = True
    board = []
    wind = Wind()

    def __init__(self, width=20, height=20, configuration=BoardType.forest, wind=Wind(), has_roads=True):
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
                if will_happen(self.board[n[0]][n[1]].prob):
                    ret_arr.append(self.board[n[0]][n[1]])

        if self.wind.speed is not WindSpeed.none:
            if self.wind.direction == Direction.north and (y - 2 >= 0):
                if will_happen(self.wind.speed):
                    ret_arr.append(self.board[n[y - 2]][x])
            elif self.wind.direction == Direction.south and (y + 2 < self.board_height):
                if will_happen(self.wind.speed):
                    ret_arr.append(self.board[n[y + 2]][x])
            elif self.wind.direction == Direction.east and (x + 2 < self.board_width):
                if will_happen(self.wind.speed):
                    ret_arr.append(self.board[n[y]][x + 2])
            elif self.wind.direction == Direction.east and (x - 2 >= 0):
                if will_happen(self.wind.speed):
                    ret_arr.append(self.board[n[y]][x - 2])

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
        else:
            for i in range(0, (w * h)):
                self.board[sc.random.randint(0, w)][sc.random.randint(0, h)] = copy.deepcopy(TerrainType[sc.random.randint(0, 1)])
        if self.has_roads:
            self.build_road()

        self.start_fire(1)
        return self.board,

    def iterate(self, dunno):
        new_burning_cells = []
        for y in range(0, self.board_height):
            for x in range(0, self.board_width):
                if self.board[y][x].isBurning:
                    new_burning_cells += self.get_new_burning_neighbors(y, x)
                    self.board[y][x].burn()
        for c in new_burning_cells:
            c.ignite()
        self.wind.change()
        return self.board,

    def show(self):
        board_out = ""
        for y in range(0, self.board_height):
            for x in range(0, self.board_width):
                board_out += " " + self.board[y][x].viz
            board_out += "\n"
        print board_out
        return board_out


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


def run():

    # inp = get_input()
    board = Board()
    do_save = False
    if do_save:
        # These are defaults for the graph
        # First set up the figure, the axis, and the plot element we want to animate
        fig = plt.figure()
        ax = plt.axes(xlim=(0, board.board_width), ylim=(0, board.board_height))
        line, = ax.plot([], [], lw=2)

        # call the animator.  blit=True means only re-draw the parts that have changed.
        anim = animation.FuncAnimation(fig, board.iterate, init_func=board.build_board, frames=500, interval=20, blit=True)

        # save the animation as an mp4.  This requires ffmpeg or mencoder to be
        # installed.  The extra_args ensure that the x264 codec is used, so that
        # the video can be embedded in html5.  You may need to adjust this for
        # your system: for more information, see
        # http://matplotlib.sourceforge.net/api/animation_api.html
        anim.save('fire_animation.mp4', fps=15)

        plt.show()
    else:
        board.build_board()
        old_board = board.show()
        i = 0
        same = False
        while i < 1000 and not same:
            board.iterate(0)
            new_board = board.show()
            # if new_board == old_board:
            # same = True
            # print "Converged"
            # else:
            old_board = new_board
            board.show()
            i += 1

run()
# def get_terrain_in_direction(self, neighbors, direction):

#    if direction == Direction.north:
#        if (self.location[0] - 1) >= 0:
#            return ((self.location[0] - 1), self.location[1])
#    elif direction == Direction.south:
#        if (self.location[0] + 1) <= self.board_height:
#            return ((self.location[0] + 1), self.location[1])
#    elif direction == Direction.east:
#        if (self.location[1] + 1) <= self.board_width:
#            return (self.location[0], (self.location[1] + 1))
#    elif direction == Direction.west:
#        if (self.location[1] - 1) >= 0:
#            return (self.location[0], (self.location[1] - 1))
#    else:
#        return (0, 0)
