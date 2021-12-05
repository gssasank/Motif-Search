package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Main {

     public static String stringGenerator()
        {

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

    public static void main(String[] args) {
	// write your code here

        int L = 15;
        int D = 5;
        int indel = 1;
        int sub = 1;

        ArrayList<String> randomStringList = new ArrayList<>();
        ArrayList<ArrayList<String>> allSubStrings = new ArrayList<>();
        ArrayList<String> answerList = new ArrayList<>();

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

        // The main logic

        for(int i = 0; i < 20; i++ ){
            System.out.println("In the first loop");
            System.out.println(i);

            ArrayList<String> loss;
            loss = allSubStrings.get(i);

            for(String subString : loss){
                int counter = 0;
                for(int j = 0; j < 20; j++ ){

                    if(i != j){
                        ArrayList<String> lossos;
                        lossos = allSubStrings.get(j);

                        for(String soss: lossos){
                            int tempCount = 0;
                            if(checkNeighbor(subString, soss, indel, sub, D)){
                                counter++;
                                tempCount++;
                            }
                            if(tempCount == 1){
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
        System.out.println(answerList.size());
    }
}
