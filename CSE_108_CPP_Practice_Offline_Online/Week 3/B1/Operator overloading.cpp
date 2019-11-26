#include <bits/stdc++.h>

using namespace std;

class Integer{
    int num;

public:
    Integer(){
        num=0;
    }
    Integer(int a){
        num=a;
        cout<<"Normal constructor"<<endl;

    }
    Integer(const Integer &a){
        num=a.num;
        cout<<"Copy constructor"<<endl;
    }

    int getInteger(){
        return num;
    }

    Integer operator+(Integer b){
        Integer temp;
        temp.num=b.num+num;

        return temp;
    }

};

void print(Integer i){
    cout<<i.getInteger()<<endl;
}


int main (){
    Integer ob1(3), ob2(5), sum;

    print(ob1);

    sum=ob1+ob2;

    print(sum);




    return 0;
}
