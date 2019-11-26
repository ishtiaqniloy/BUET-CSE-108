#include <iostream>
#include <cstdio>
#include <cstring>
#include <cmath>
#include <cstdlib>

using namespace std;

class String;
String upToLowStr(String st);

class String{
    char *s;
    int len;

public:
    String();
    String(char *p);
    String(const String &st);
    ~String();
    char * getString();
    int getLen();
    void setString(char *p);

    char& operator[](int i);
    String operator=(String st);
    String operator=(char* p);
    String operator++(int );
    bool operator==(String str);
    bool operator>(String str);
    bool operator<(String str);

};

String::String(){
    //cout<<"Constructing..."<<endl;
    s= new char[100];
    s[0]=0;
    len=0;
}

String::String(char *p){
    //cout<<"Constructing..."<<endl;
    len=strlen(p);
    s= new char[len+15];
    strcpy(s, p);

}

String::String(const String &st){
    //cout<<"Copy Constructor..."<<endl;
    len=st.len;
    s= new char[len+15];

    strcpy(s, st.s);

}

String::~String(){
    //cout<<"Destroying..."<<endl;
    delete []s;

}

char * String::getString(){
    return s;
}

int String::getLen(){
    return len;
}

void String::setString(char *p){
    len=strlen(p);
    delete []s;
    s= new char[len+15];

    strcpy(s, p);
}

char& String::operator[](int i){
    return s[i];
}

String String::operator=(String st){
    len=st.len;
    s= new char[len];

    strcpy(s, st.s);

    return *this;

}

String String::operator=(char* p){
    len=strlen(p);
    delete []s;
    s= new char[len+15];

    strcpy(s, p);

    return *this;

}

String String::operator++(int ){
    String temp = *this;
    for(int i=0; i<len; i++)
        s[i]++;

    return temp;

}


bool String::operator==(String str){
    String str1(s), str2(str.s);

    str1=upToLowStr(str1);
    str2=upToLowStr(str2);

    char *s1= new char[len+15];
    char *s2= new char[str.len+15];

    s1=str1.getString();
    s2=str2.getString();


    /*if(len!=str.len)
        return false;

    for(int i=0; i<len+1; i++){
        if(s1[i]!=s2[i])
            return false;
    }

    return true;*/

    if(strcmp(s1,s2)==0)
        return true;
    else
        return false;

}

bool String::operator>(String str){
    int i=0;

    String str1(s), str2(str.s);

    str1=upToLowStr(str1);
    str2=upToLowStr(str2);

    char *s1= new char[len+15];
    char *s2= new char[str.len+15];

    s1=str1.getString();
    s2=str2.getString();

    if(strcmp(s1,s2)==1)
        return true;
    else
        return false;

    /*while(s1[i] && s2[i]){
        if(s1[i]==s2[i])
            i++;

        else if(s1[i]>s2[i])
            return true;

        else if(s1[i]<s2[i])
            return false;

    }

    if(s2[i]==0 && s1[i])
        return true;

    return false;*/

}

bool String::operator<(String str){
    int i=0;

    String str1(s), str2(str.s);

    str1=upToLowStr(str1);
    str2=upToLowStr(str2);

    char *s1= new char[len+15];
    char *s2= new char[str.len+15];

    s1=str1.getString();
    s2=str2.getString();

    if(strcmp(s1,s2)==-1)
        return true;
    else
        return false;

    /*while(s1[i] && s2[i]){
        if(s1[i]==s2[i])
            i++;

        else if(s1[i]<s2[i])
            return true;

        else if(s1[i]>s2[i])
            return false;

    }

    if(s2[i]==0 && s1[i])
        return true;

    return false;*/

}


String upToLowStr(String st){
    int i, len;
    len=st.getLen();

    char * str1= new char[len+15];
    char str2[len+15];
    str1=st.getString();

    for(i=0; i<len+1; i++){
        if(str1[i]>='A' && str1[i]<='Z')
            str2[i]=str1[i]-'A'+'a';

        else
            str2[i]=str1[i];

    }

    String temp(str2);

    return temp;
}



String replaceAll(String str,char a,char b){
    int len=str.getLen();
    for (int i=0; i<len; i++){
        if(str[i]==a){
            str[i]=b;
        }
    }

    return str;

}

void bubbleSort(String strArray[], int n){
    bool flag=true;
    String temp;

    while(flag){
        flag=false;
        for(int i=0; i<n-1; i++){
            if(strArray[i]>strArray[i+1]){
                flag=true;
                temp=strArray[i];
                strArray[i]=strArray[i+1];
                strArray[i+1]=temp;

            }

        }

    }


}

int binarySearch(String strArray[], int n, String target){
    int f=0, l, m;
    l=n-1;

    while(f<=l){
        m=(f+l)/2;
        if(strArray[m]==target){
            return m;
        }

        else if(strArray[m]>target){
            l=m-1;
        }

        else{
            f=m+1;
        }

    }

    return -1;

}

int main() {

    int n;
    char p[1000];
    String s("BUET");

    cout<<"Number of strings = ";
    cin>>n;
    String strArray[n];

    for(int i=0;i<n;i++){
        cin>>p;
        strArray[i].setString(p);
    }

    bubbleSort(strArray,n);

    for(int i=0;i<n;i++){
        cout<<strArray[i].getString()<<endl;
    }

    cout<<binarySearch(strArray,n,s)<<endl;

    s=replaceAll(s,'T','L'); // s should now contain "BUEL"

    cout<< (s++).getString()<<endl;
     // s should contain "CVFM" , but in the console the output should be "BUEL"
    cout<<s.getString()<<endl; // the output should be "CVFM"



//testing codes are the following


 /*   int n, i, srch=-2;
    char p[1000];

    cout<<"Number of strings = ";
    cin>>n;
    String strArray[n];

    String str("Hello World!");
    //cout<<str.getString()<<endl;



    for(i=0; i<n; i++){
        cin>>p;
        strArray[i].setString(p);
        //cout<<strArray[i].getString()<<endl;

    }

    bubbleSort(strArray, n);

    for(i=0; i<n; i++){
        cout<<strArray[i].getString()<<endl;
    }

    cout<<"Enter search string"<<endl;
    cin>>p;
    str.setString(p);
    srch=binarySearch(strArray, n, str);
    cout<<srch<<endl;


    str.setString("Hello World!");

    cout<<str.getString()<<endl;
    str=replaceAll(str, 'l', 'd');
    cout<<str.getString()<<endl;

    cout<<(str++).getString()<<endl;
    cout<<str.getString()<<endl;



     /*if(strArray[0]>strArray[1])
        cout<<"true"<<endl;
    else
        cout<<"false"<<endl;

    cout<<strArray[0].getString()<<endl;
    cout<<strArray[1].getString()<<endl;*/


    return 0;


}


