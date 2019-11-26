#include <iostream>

using namespace std;

class myclass{
   private:
     int x;

    public:
   int getx();
   void setx(int a);


};

int main(){
    myclass cl;

    int x;

    cout<<"give a number\n";

    cin>>x;
    cl.setx(x);

    cout<<"The number is : "<<cl.getx();

    return 0;


}

int myclass::getx(){
    return x;
}

void myclass::setx(int a){
    x=a;
}
