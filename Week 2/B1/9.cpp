#include <iostream>
#include <cstdio>
#include <cstring>
#include <cmath>
#include <cstdlib>

using namespace std;

class String{
    char *str;
    int len;

public:
    String();
    String(char *s);
    String(int n);
    void concat(char *s);
    void concat(int a);
    void concat(double d);
    void concat(char c);
    void concat(int a, char c);
    void showString();
    int getLength();



};

String::String(){
    str= new char[100];
    str[0]=0;
    len=0;
}

String::String(char *s){
    str= new char[100];
    strcpy(str, s);
    len=strlen(s);

}

String::String(int n){
    str= new char[100];
    int i;
    for(i=0; i<n; i++){
        str[i]=' ';
    }
    str[n]=0;
    len=strlen(str);

}

void String::concat(char *s){

    strcat(str, s);
    len=strlen(str);

}


void String::concat(int a){
    char source[20];
    itoa(a, source, 10);
    strcat(str, source);
    len=strlen(str);

}

void String::concat(double d){
    char source[20];
    int i;
    sprintf(source, "%lf", d);
    strcat(str, source);
    len=strlen(str);
    for(i=len-1; str[i]=='0'; i--){
        str[i]=0;
    }
    len=strlen(str);

}

void String::concat(char c){
    str[len]=c;
    str[len+1]=0;
    len=strlen(str);

}

void String::concat(int a, char c){
    int i;
    for(i=0; i<a; i++){
        str[len+i]=c;
    }
    str[len+a]=0;
    len=strlen(str);

}

void String::showString(){
    cout<<str<<endl;

}

int String::getLength(){
    return len;

}


int main() {
    String s("Hello World!");

    s.showString();
    cout<<s.getLength()<<endl;

    s.concat(235.4001);
    s.showString();
    cout<<s.getLength()<<endl;




    return 0;
}


