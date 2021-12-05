package com.company;

import java.io.*;
import java.util.*;

public class Main {

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

    public static String slice_range(String s, int startIndex, int endIndex) {
        if (startIndex < 0) startIndex = s.length() + startIndex;
        if (endIndex < 0) endIndex = s.length() + endIndex;
        return s.substring(startIndex, endIndex);
    }

    public static int editDistance(String x, String y, int indel, int sub){
         int m = x.length();
         int n = y.length();

         int[][] E = new int[m+1][n+1];

        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++) {
                E[i][j] = 0;
            }
        }

        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){

                if(i == 0){
                    E[i][j] = j;
                }

                else if(j == 0){
                    E[i][j] = i;
                }

                else{
                    int clap = 0;

                    if(x.charAt(i-1) != y.charAt(j-1)){
                        clap = sub;
                    }

                    E[i][j] = Math.min((E[i][j-1]+indel), Math.min((E[i-1][j]+indel), E[i-1][j-1] + clap) );

                }
            }
        }
        return(E[m][n]);
    }

    public static boolean checkNeighbor(String m1, String m2, int indel, int sub, int D){
         return(editDistance(m1, m2, indel, sub) <= D);
    }

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here

        Scanner inFile = new Scanner(new FileReader("Data.txt"));

        int L = inFile.nextInt();
        int D = inFile.nextInt();
        int indel = inFile.nextInt();
        int sub = inFile.nextInt();

        ArrayList<String> randomStringList = new ArrayList<>();
        ArrayList<ArrayList<String>> allSubStrings = new ArrayList<>();
        ArrayList<String> answerList = new ArrayList<>();
        ArrayList<ArrayList<String>> allContainer = new ArrayList<>();

        System.out.println("The value of L is " + L + " and the value of D is " + D);
        System.out.println("The value of indel is " + indel + " and the value of sub is " +sub);
//        System.out.println("The program typically takes 3-4 minutes to execute.");
//        System.out.println("The number of strings varies from around 20 to around 60.");
        System.out.println("The final output will be available in Out.txt after the execution.");
        System.out.println("Printing the string values as they are found:");

        for(int i = 0; i < 20 ; i++){
            randomStringList.add(stringGenerator());
        }

        for (String string: randomStringList){
            ArrayList<String> listOfSubstrings = new ArrayList<>();
            for(int i = 0; i <= (600-L); i++){
                int temp = i + L ;
                String blip = slice_range(string, i, temp);
                listOfSubstrings.add(blip);
            }
            allSubStrings.add(listOfSubstrings);
        }

        for(String string: randomStringList){
            ArrayList<String> listOfSubstrings = new ArrayList<>();
            for(int i = (L-D); i <= (L+D); i++){
                for (int j = 0; j <= (600-i); j++){
                    int temp = j + i ;
                    String blip = slice_range(string, j, temp);
                    listOfSubstrings.add(blip);
                }
            }
            allContainer.add(listOfSubstrings);
        }

//        System.out.println(allContainer.get(1));

//        for(String list: allContainer.get(1)){
//            System.out.println(list);
//
//        }

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
                            if(flag){
                                break;
                            }
                        }
                    }
                }

                if(counter == 19){
                    System.out.println(subString);
                    answerList.add(subString);
                }
            }
        }

        System.out.println("The number of strings is " + answerList.size());

        // Code to print output to Out.txt after printing to console

        PrintStream o = new PrintStream("Out.txt");
        System.setOut(o);

        for(String sequence: answerList){
            System.out.println(sequence);
        }

        System.out.println("the number of strings: " + answerList.size());


    }
}
