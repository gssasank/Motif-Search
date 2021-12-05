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

    # for i in range(m+1):
    #     E[i][0] = i 

    # for j in range(n+1):
    #     E[0][j] = j


    for i in range(m + 1):
        for j in range(n + 1):

            if i == 0:
                E[i][j] = j
            
            elif j == 0:
                E[i][j] = i
            
            else:
                clap = 0 if x[i-1] == y[j-1] else sub

                E[i][j] =     min(E[i][j-1] + indel,       # Insert
                                    E[i-1][j] + indel,     # Remove
                                    E[i-1][j-1] + clap)    # Replace

    return(E[m][n])

def check_neighbor(m1, m2):
    return edit_distance(m1, m2, 1, 1) <= D


all_sub_strings = []
answer_array = []

for string in random_string_list:
    list_of_substrings = [string[i:i+L] for i in range(601-L)] # this is because, only 585 sub-strings of length 15 will exist.
    all_sub_strings.append(list_of_substrings)

for i in range(20):
    print("In the first loop again!" , i)
    l_of_sub_strings = all_sub_strings[i]
    for sub_string in l_of_sub_strings:
        counter = 0
        for j in range(20):
            if i != j:
                l_of_sub_strings_of_other_strings = all_sub_strings[j]
                for sub_string_of_other_strings in l_of_sub_strings_of_other_strings:
                    if check_neighbor(sub_string, sub_string_of_other_strings):
                        counter += 1

        if counter == 19:
            answer_array.append(sub_string)
            print(sub_string)

print("Number of Sub-Strings: ", len(answer_array))