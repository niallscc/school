#!usr/bin/python


def fibonacci(cur_fib):
    if len(cur_fib) < 2:
        print "error bad int"
    else:
        return cur_fib[0] + cur_fib[1]


ret_val = fibonacci([1, 1])
print ret_val
