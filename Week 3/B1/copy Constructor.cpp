#include <iostream>
#include <cstring>
#include <cstdio>

using namespace std;

class Integer{
    int num;

public:
    Integer(){
        num=0;
        cout<<"Normal constructor"<<endl;

    }
    Integer(const Integer &a){
        num=100;
        cout<<"Copy constructor"<<endl;
    }

    int getInteger(){
        return num;
    }

};

void print(Integer i){
    cout<<i.getInteger()<<endl;
}


int main (){
    Integer i;

    print(i);


    return 0;
}
