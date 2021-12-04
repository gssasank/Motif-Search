import random
length_of_string = 600
req_set  = ("A","C","G","T")


L = 15
D = 5

random_string_list = []

for _ in range(20):
    random_string = "".join(random.choice(req_set) for _ in range(length_of_string))
    random_string_list.append(random_string)

print(random_string_list)