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
def edit_distance_dp(x, y):
    m = len(x)
    n = len(y)

    # Create a table to store results of subproblems
    dp = [[0 for _ in range(n + 1)] for _ in range(m + 1)]

    # Fill d[][] in bottom up manner
    for i in range(m + 1):
        for j in range(n + 1):

            # If first string is empty, only option is to
            # insert all characters of second string
            if i == 0:
                dp[i][j] = j    # Min. operations = j

            # If second string is empty, only option is to
            # remove all characters of second string
            elif j == 0:
                dp[i][j] = i    # Min. operations = i

            # If last characters are same, ignore last char
            # and recur for remaining string
            elif x[i-1] == y[j-1]:
                dp[i][j] = dp[i-1][j-1]

            # If last character are different, consider all
            # possibilities and find minimum
            else:
                dp[i][j] = 1 + min(dp[i][j-1],        # Insert
                                   dp[i-1][j],        # Remove
                                   dp[i-1][j-1])    # Replace

    return dp[m][n]

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
    for substring in list_of_substrings:
        pass