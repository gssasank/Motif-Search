#include <bits/stdc++.h>
using namespace std;
 

 
// Returns a string of random alphabets of
// length n.
string createRandomString()
{
    char alphabet[4] = {'A','C','G','T' };
    int n = 600;
    string res = "";
    for (int i = 0; i < n; i++)
        res = res + alphabet[rand() % 26];
     
    return res;
}
 
// Driver code
int main()
{
   srand(time(NULL));
   int n = 20;

   vector<string> randomStringList;

   for(int i = 0; i < n; i++){
       string temp = createRandomString();
       randomStringList.push_back(temp);
   }

    // for(int i = 0; i < n; i++){
    //     cout << randomStringList[i] << endl;
    // }

    

   cout << randomStringList[1] << endl;
   return 0;
}