import random
length_of_string = 600
req_set  = ("A","C","G","T")


L = 15
D = 5

random_string_list = []

for _ in range(20):
    random_string = "".join(random.choice(req_set) for _ in range(length_of_string))
    random_string_list.append(random_string)

# print(random_string_list)

# Given to you are integers L and D and the alphabet set Σ{A,C,G,T}. Write a program to implement the following:
# Randomly generate 20 strings S1, S2 … S20 of length 600 each using alphabet set Σ.
# For each string i from 1 … 20
#   For each substring M in string Si, where |M| = L:
#       If a neighbor of M occurs in each of the other 19 strings, then output M;
#           where a string M’ is considered as a Neighbor of M if Edit distance (M, M’) <= D.


# for i in range(len(random_string_list)):
#     for j in range(len(random_string_list)):
#         if i != j:
#             for k in range(len(random_string_list[i])):
#                 if random_string_list[i][k:k+L] == random_string_list[j][k:k+L]:
#                     print(random_string_list[i][k:k+L])

for string in random_string_list:
    list_of_substrings = [string[i:i+L] for i in range(586)] # this is because, only 585 sub-strings of length 15 will exist.
    print(list_of_substrings)