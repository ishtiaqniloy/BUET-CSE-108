#include <iostream>
#include <cstdio>
#include <cstring>
#include <cstdlib>

using namespace std;

class String{
private:
    char *s;
    int len;

public:
    String();
    String(char *p);
    String(const String &p);
    ~String();
    char *getString();

    String operator+(char *p);
    String operator+(int n);
    String operator*(int n);
    String operator-();
    String operator=(String p);





};

String::String(){
    s= new char[100];
    s[0]=0;
    len=0;
}


String::String(char *p){
    len=strlen(p);
    if(100>5*len)
        s= new char[100];
    else
        s= new char[5*len];

    strcpy(s,p);


}


String::String(const String &p){
    len=strlen(p.s);
    if(100>5*len)
        s= new char[100];
    else
        s= new char[5*len];

    strcpy(s,p.s);

}

String::~String(){
    delete []s;
}

char * String::getString(){
    return s;
}

String String::operator+(char *p){
    strcat(s,p);
    len=strlen(s);

    return *this;


}

String String::operator+(int n){
    char p[20];
    itoa(n,p,10);
    strcat(s,p);
    len=strlen(s);

    return *this;

}

String String:: operator*(int n){
    char *p;
    p=new char[len*n+50];

    strcpy(p,s);

    for(int i=1; i<n;i++){
        strcat(p,s);
    }

    len=strlen(p);

    s=p;

    return *this;


 }

String  String::operator-(){
    len=strlen(s);
    char *p=new char[len+50];

    for(int i=0; i<len; i++){
        p[i]=s[len-1-i];
    }
    p[len]=0;

    s=p;

    return *this;

}


String String::operator=(String p){
    char *str;

    str= new char[len+50];

    strcpy(str,p.s);

    s=str;
    len=strlen(s);

    return *this;

}

int main(){
    String s("BUET");
    String s1, s2;

    cout <<s.getString()<<endl;

    s1 = s+"CSE";
    cout<<s1.getString()<<endl;

// Should concat "108" at the end of "BUETCSE"
    s1 = s+108;
    cout<<s1.getString()<<endl;

// Should reverse "BUETCSE108" and make it “801ESCTUEB”
    s= - s;
    cout<<s.getString()<<endl;

// s1 should be concatenated 3 times and stored in s2. If s1 contains “abc”, s2 should now contain “abcabcabc”
    s2=s1*3;
    cout<<s2.getString()<<endl;




    return 0;
}
