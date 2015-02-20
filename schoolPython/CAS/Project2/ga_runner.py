#!usr/bin/python
import random
from GA import ga
import numpy as np


def ga_runner(num_generations):

    board = get_board()
    print board
    print find(board, '8')
    # strategies = []

    # for i in range(0, 200):
    #     individuals = []
    #     for j in range(0, 243):
    #         individuals.append(random.randint(0, 7))
    #     strategies.append(individuals)

    # strategy, fitness = ga(strategies, num_generations, np.array(board))
    # print "best strategy produces a fitness of: %i, the stratgy is: %s" % (
    #     strategy, fitness)


def get_board():
    # board = np.array([['0' for col in range(10)] for row in range(10)])
    board = [['0' for col in range(10)] for row in range(10)]
    num_cans = 50

    while num_cans > 0:
        x = random.randint(0, 9)
        y = random.randint(0, 9)

        if board[x][y] == '0':
            board[x][y] = '1'
            num_cans = num_cans - 1
    board[0][0] = board[0][0] + ' 8'
    return board

# decision List:
#   0 = MoveNorth,
#   1 = MoveSouth,
#   2 = MoveEast,
#   3 = MoveWest,
#   4 = StayPut,
#   5 = PickUp,
#   6 = RandomMove


def calc_fitness(decision, board):

    row, col = find(board, 8)
    if decision == 0:
        return calculate_move(row, col, row + 1, col)
    if decision == 1:
        return calculate_move(row, col, row - 1, col)
    if decision == 2:
        return calculate_move(row, col, row, col + 1)
    if decision == 3:
        return calculate_move(row, col, row, col - 1)
    if decision == 4:
        return 0
    if decision == 5:
        return calc_pickup(row, col, board)
    if decision == 6:
        return calc_fitness(random.randint(0, 5), board)


def calc_pickup(prev_row, prev_col, row, col, board):
    if(board[row, col] == '1'):
        board[prev_row][prev_col] = '0'
        board['']
        return 10
    else:
        return -1


def calculate_move(board, row, col):
    if row >= 0 and row < 10 and col >= 0 and col < 10:

        return 0
    else:
        return -5


def find(l, elem):
    for row, i in enumerate(l):
        try:
            column = i.index(elem)
        except ValueError:
            continue
        return row, column
    return -1

ga_runner(1000)
