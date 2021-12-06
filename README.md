# **DAA ASSIGNMENT**

## **ABSTRACT:**

Motifs, also known as Transcription Factor Binding Sites, are biological patterns of great interest. Motif Search algorithms identify the conserved motifs within a given set of DNA sequences. There are different versions of Motif Search Problem, such as Simple Motif Search, Planted Motif Search, Edited Motif Search, Quorum Motif Search. In this exercise, you are supposed to solve a simplified version of Edit-distance based Motif Search.

# **TASK:**

Given to you are integers L and D and the alphabet set Σ {A, C, G, T}. Write a program to implement the following:

- Randomly generate 20 strings S1, S2 … S20 of length 600 each using alphabet set Σ.
- For each string i from 1 … 20:
  - For each substring M in string Si, where |M| = L:
    - If a neighbour of M occurs in each of the other 19 strings, then output M; where a string M&#39; is considered as a Neighbour of M if Edit distance (M, M') <= D.

## **DIRECTIONS FOR RUNNING THE PROGRAM:**

The program file is named Main.java, the file containing the input data is named Data.txt and the file containing the output data is named Out.txt. Make sure that the Data.txt file is in the same directory as the Main.java file. The output is printed into the console as the matching strings are found and when the execution of the program is finished, the Out.txt file is populated with the matching strings.

The Data.txt file should have four integers separated by spaces, in the specified order: L D indel sub.

## **EDIT DISTANCE:**

**Definition of Edit Distance:**

Given two strings _X_ and _Y_, the edit distance between them is the minimum number of edit operations required to transform _X_ into _Y_.

The edit operations are Insertion, deletion, and substitution. They are defined as follows:

• _Substitution_ – Replace a single character from pattern P with a different character in text T, such as changing &quot;ACGT&quot; to &quot;ACGC.&quot;

• _Insertion_ – Insert a single character into pattern P to help it match text T, such as changing &quot;ACCG&quot; to &quot;ACCGT.&quot;

• _Deletion_ – Delete a single character from pattern P to help it match text T, such as changing &quot;GTTCA&quot; to &quot;TTCA.&quot;

It was given that both insertion and deletion have the same cost and that is represented with _indel._ The cost for substitution is taken as _sub_.There are two approaches with which we can calculate the edit distance. The first method is by using recursion, but the problem with using recursion is the extremely high time complexity (the worst-case time complexity is almost equal to 3n).

The second method is by using **Dynamic Programming.** For that we need to initialize a matrix E[0…p][0…q], where E[i, j] represents the number of transformations required to convert X[1...i] to Y[1…j]. The value at E[p, q] will give you the Edit Distance. Using the algorithm mentioned in the question, the function to calculate Edit Distance is as follows:

```Java
public static int editDistance(String x, String y, int indel, int sub){
         int m = x.length();
         int n = y.length();

         int[][] E = new int[m+1][n+1];

        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){

                if(i == 0)
                    E[i][j] = j;

                else if(j == 0)
                    E[i][j] = i;

                else{
                    int clap = 0;
                    if(x.charAt(i-1) != y.charAt(j-1))
                        clap = sub;

                    E[i][j] = Math.min((E[i][j-1]+indel), Math.min((E[i-1][j]+indel), E[i-1][j-1] + clap) );
                }
            }
        }
        return(E[m][n]);
    }
```

As you can see, the above Java method takes the two strings it is comparing (x and y) as the input along with the value of _indel_ and _sub_. The worst-case time complexity of this algorithm would be **O(m\*n)**.

## **THE MAIN METHOD:**

```Java
 // Input taken from file
        Scanner inFile = new Scanner(new FileReader("Data.txt"));

        int L = inFile.nextInt();
        int D = inFile.nextInt();
        int indel = inFile.nextInt();
        int sub = inFile.nextInt();
```

The first few lines of the Main method were to take the input from Data.txt file and to assign the values to the respective variables.

```Java
        for(int i = 0; i < 20 ; i++){
            randomStringList.add(stringGenerator());
        }
```

Using the self-defined stringGenerator() method, 20 random strings of length 600, containing the letters A, C, G and T were generated.

```Java
        for (String string: randomStringList){
            ArrayList<String> listOfSubstrings = new ArrayList<>();
            for(int i = 0; i <= (600-L); i++){
                int temp = i + L ;
                String blip = sliceRange(string, i, temp);
                listOfSubstrings.add(blip);
            }
            allSubStrings.add(listOfSubstrings);
}
```

Each of the 600-character strings were split into sub-strings of length L and all these new sub-strings were stored in an ArrayList called allSubStrings. For example, as given in the question when L = 15, all the sub-strings in the ArrayLists are of length 15.

```Java
for(String string: randomStringList){
            ArrayList<String> listOfSubstrings = new ArrayList<>();
            for(int i = (L-D); i <= (L+D); i++){
                for (int j = 0; j <= (600-i); j++){
                    int temp = j + i ;
                    String blip = sliceRange(string, j, temp);
                    listOfSubstrings.add(blip);
                }
            }
            allContainer.add(listOfSubstrings);
        }
```

A second ArrayList named allContainer is created and it contains 20 ArrayLists of sub-strings of lengths L-D to L+D. For example, when we consider L = 15 and D = 5, the length of sub-strings stored in the ArrayLists range from 10 to 20.

```Java
for(int i = 0; i < 20; i++ ){
            ArrayList<String> loss;
            loss = allSubStrings.get(i);
            for(String subString : loss){
                int counter = 0;
                for(int j = 0; j < 20; j++ ){
                    if(i != j){
                        ArrayList<String> lossos;
                        lossos = allContainer.get(j);
                        for(String soss: lossos){
                            boolean flag = false;
                            if(checkNeighbor(subString, soss, indel, sub, D)){
                                counter++;
                                flag = true;
                            }
                            if(flag)
                                break;
                        }
                    }
                }
                if(counter == 19){
                    System.out.println(subString);
                    answerList.add(subString);
                }
            }
        }
```

This is the core logic of the program. For every string of length L, corresponding to the 600-character random string i.e., for every string in allSubStrings, we compare them with every sub-string in allContainer, i.e., every sub-string of length L-D to L+D. The loop goes on until the indices of the ArrayLists are unequal. This is because, when the indices of the ArrayLists are equal, then that iteration is skipped as we only want to check the neighbours.

Then, the checkNeighbor() method is used to check whether or not the edit distance is less than or equal to D, if the method returns True then the counter is increased to one, the present loop is broken and the sub-strings from the next ArrayList in allSubStrings are compared to the sub-strings in the next ArrayList in allContainer(unless of course their indices are same, in which case that iteration is skipped as explained above). When the counter reaches 19, the sub-string&#39;s value is added to the answerList ArrayList and is also printed to the console directly. This continues until all the comparisons are completed.

The worst-case time complexity of this part is **O(n****2 ****\*m**** 2****\*n\*m)**. The n2 component is for the first two loops, the m2 component is for the second two loops. The n\*m component is of the edit distance function. Roughly, we can assume the time complexity to me of the order O(n6).

```Java
// Code to print output to Out.txt after printing to console

        PrintStream o = new PrintStream("Out.txt");
        System.setOut(o);

        for(String sequence: answerList){
            System.out.println(sequence);
        }

        System.out.println("The number of strings is " + answerList.size());
```

This part of the code is responsible for printing the output in Out.txt. Once the execution of the program is finished, if a file named Out.txt does not exist, it is created and then written to and if it does exist, it is directly written to. Each string that has a neighbour is printed along with the total number of strings found to be matching.

```
GCTACTCATAGGTTA
CTATTTCATGAGTAC
CGACGAGCTCTGATC
TGTCGTACTCCAGAG
CGCTCGACTAGCTAG
CATAGCTTGTCAGCG
ATAGCTTGTCAGCGA
The number of strings is 7
```

Apart from the writing the output to the file, the matching strings are also directly printed to the console as they are found, along with the entered values of L, D, indel, sub. The above is output for 15 5 1 2.

```Java
public static String stringGenerator(){

            String alphabet = "ACGT";
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            int length = 600;

            for(int i = 0; i < length; i++) {
                int index = random.nextInt(alphabet.length());
                char randomChar = alphabet.charAt(index);
                sb.append(randomChar);
            }
            return(sb.toString());
     }
```

This is the Java method used to generate random strings of length 600, containing the letters A, C, G, and T.

```Java
    public static boolean checkNeighbor(String m1, String m2, int indel, int sub, int D){
         return(editDistance(m1, m2, indel, sub) <= D);
    }
```

This is the Java method used to check whether two given strings are neighbours or not.

## **POTENTIAL IMPROVEMENTS:**

This approach has massive room for improvement. The worst-case time complexity and space complexity for finding the edit distance is O(m\*n). The quadratic space used to store the dynamic programming table (in our case it is E) can be dramatically improved. One way to improve the space complexity to around O(m) is by using a divide and conquer algorithm.

Only O(_min_(m, n)) (i.e., essentially O(m) or O(n)) linear space is needed to compute E[m, n]. We need to only maintain two active rows (or columns) of the E matrix in the memory to compute the final value. The entire matrix will be required only if we need to reconstruct the actual sequence alignment.

During one pass of the algorithm hypothesized above, to compute E[m, n], we will identify which middle-element cell E[m/2, x] was used to optimize E[m, n]. Now, our problem is reduced to finding the best possible paths from E[1, 1] to E[m/2, x] and from E[m/2, x] to E[m/2, n].

Using recursion, both of these individual problems can be solved easily (We just used a divide-and-conquer approach to reduce a bigger problem into sub-parts which we will now solve using recursion). Each time half the matrix elements are removed from consideration, and hence, the worst-case time complexity still remains O(m\*n). The proposed algorithm provides much better space complexity (linear).

For specific pairs of characters, we can modify the edit distance function to use different costs for insertion, deletion, and the substitutions of specific pairs of characters. The costs that you should use will depend on what you are planning to do with the alignment. The most common cost assignment charges the same for insertion, deletion, or substitution (For example, 15 5 1 1, here insert = delete = sub = 1).

Another approach for identifying specific pairs is using filtration, by eliminating the parts of the string where we know there is no chance of finding the pattern. Splice the m-length pattern into l+1 different pieces and if there is a match with at most l variations, then at least one of these pieces will be an exact match up.

Currently the worst-case time complexity of the main program is around **O(n****6****)**. This is due to the presence of four nested for-loops.Each and every value in both the 2D arrays (ArrayLists) is checked with respect to each other. One potential improvement that can be implemented is the use of Hash Maps or similar data-structures like Dictionaries in Python. We can create Hash Maps with the keys being the first ArrayList values and the values being the sub-strings with which those strings are compared. The time complexity of getting a value given a key in a hash map is O(1).
