import random
length_of_string = 600
req_set  = ("A","C","G","T")


L = 15
D = 5

random_string_list = []

for _ in range(20):
    random_string = "".join(random.choice(req_set) for _ in range(length_of_string))
    random_string_list.append(random_string)


# Algorithm for Edit Distance using dynamic programming 
def edit_distance(x, y, indel, sub):
    m = len(x)
    n = len(y)

    # Create a table to store results of subproblems
    E = [[0 for _ in range(n + 1)] for _ in range(m + 1)]

    for i in range(m+1):
        E[i][0] = i 

    for j in range(n+1):
        E[0][j] = j


    for i in range(m + 1):
        for j in range(n + 1):

            clap = 0 if x[i] == y[j] else sub

            E[i][j] =     min(E[i][j-1] + indel,       # Insert
                                E[i-1][j] + indel,     # Remove
                                E[i-1][j-1] + clap)    # Replace

    return(E[m][n])

def check_neighbor(m1, m2):
    return edit_distance(m1 - m2) <= D

# print(random_string_list)

# Given to you are integers L and D and the alphabet set Σ{A,C,G,T}. Write a program to implement the following:
# Randomly generate 20 strings S1, S2 … S20 of length 600 each using alphabet set Σ.
# For each string i from 1 … 20
#   For each substring M in string Si, where |M| = L:
#       If a neighbor of M occurs in each of the other 19 strings, then output M;
#           where a string M’ is considered as a Neighbor of M if Edit distance (M, M’) <= D.



all_sub_strings = []

for string in random_string_list:
    list_of_substrings = [string[i:i+L] for i in range(601-L)] # this is because, only 585 sub-strings of length 15 will exist.
    all_sub_strings.append(list_of_substrings)

for i in range(20):
    l_of_sub_strings = all_sub_strings[i]
    for j in l_of_sub_strings:
        
