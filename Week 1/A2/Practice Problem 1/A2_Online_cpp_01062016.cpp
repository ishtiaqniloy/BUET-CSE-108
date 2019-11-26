#include <iostream>
#include <cmath>

using namespace std;

class Point{
    int x;
    int y;
public:
    Point();
    Point(int a, int b);
    double originDistance();
    void comparePoint(Point a);
    void showPoint();

    int getX();
    int getY();

};

Point::Point(){
    x=0;
    y=0;

}

Point::Point(int a, int b){
    x=a;
    y=b;

}

double Point::originDistance(){
    return sqrt(x*x+y*y);


}

void Point::comparePoint(Point a){
    int ax, ay, bx, by;

    ax=a.getX();
    ay=a.getY();

    bx=x;
    by=y;

    cout<<ax<<" "<<ay<<" "<<bx<<" "<<by<<endl;

    if(ax==bx && ay==by)
        cout<<"Same"<<endl;

    else if(ax==bx && ay>by)
        cout<<"Up"<<endl;

    else if(ax==bx && ay<by)
        cout<<"Down"<<endl;

    else if(ax>bx && ay==by)
        cout<<"Right"<<endl;

    else if(ax<bx && ay==by)
        cout<<"Left"<<endl;

    else if(ax=bx && ay<by)
        cout<<"Down"<<endl;

    else if(ax>bx && ay>by)
        cout<<"Right-up"<<endl;

    else if(ax>bx && ay<by)
        cout<<"Right-down"<<endl;

    else if(ax<bx && ay<by)
        cout<<"Left-down"<<endl;

    else if(ax<bx && ay>by)
        cout<<"Left-up"<<endl;

    else
        cout<<"Error"<<endl;

}

void Point::showPoint(){

    cout<<x<<","<<y<<endl;

}

int Point::getX(){

    return x;

}

int Point::getY(){
    return y;

}


int main(){
    Point a, b(2,2), c(-5,3), d(3,2);

    a.showPoint(); //output: (0,0)

    b.showPoint(); //output: (2,2)

    cout<<b.originDistance()<<endl; //output: 2.82843

    a.comparePoint(b); //output: (2,2) is in right-up position of (0,0)

    b.comparePoint(c); //output: (-5,3) is in left-up position of (2,2)

    b.comparePoint(d); //output: (3,2) is in right position of (2,2)

    c.comparePoint(c); //output: (-5,3) is in same position of (-5,3)






    return 0;

}

