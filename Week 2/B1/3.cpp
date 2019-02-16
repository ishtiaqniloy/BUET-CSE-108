#include <iostream>
#include <cstring>

using namespace std;

class String{
    char str[100];
    int length;

    public:
    String(char *s);
    void showString();
    int getLength();
    void concat(char *s);



};

String::String(char *s){
    strcpy(str,s);
    length=strlen(s);
}

void String::showString(){
    cout<<str<<endl;
}

int String::getLength(){
    return length;

}

void String::concat(char *s){
    strcat(str,s);
    length+=strlen(s);
}

int main(){
    String st("Hello World!!!");

    st.showString();
    cout<<"Length= "<<st


    st.concat(" This is C++");

    st.showString();



    return 0;
}
