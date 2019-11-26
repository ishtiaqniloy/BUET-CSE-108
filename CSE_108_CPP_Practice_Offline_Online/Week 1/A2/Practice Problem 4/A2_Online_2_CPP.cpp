#include <iostream>

using namespace std;

class Point{
    double x;
    double y;

public:
    Point();
    Point(double a, double b);
    double getx();
    double gety();

};

Point::Point(){

}

Point::Point(double a, double b){
    x=a;
    y=b;
}

double Point::getx(){
    return x;
}

double Point::gety(){
    return y;
}

class Line{
private:
    Point a;
    Point b;

public:
    Line(Point m, Point n);
    Point ratio(int m, int n){  ///returns the point that divides the line segment by m:n ratio
        double x, y;

        x=(n*a.getx()+m*b.getx())/(m+n);
        y=(n*a.gety()+m*b.gety())/(m+n);
        Point r(x,y);
        return r;

    }
};


Line::Line(Point m, Point n){
    a=m;
    b=n;
}


int main(){

    Point p1(0,5), p2(10,10), p3;  ///create Point object using 2 types of constructor
    Line l1(p1,p2);   ///create line object

    p3=l1.ratio(2,1);
    ///then print point p3

    cout<<p3.getx()<<","<<p3.gety()<<endl;

    return 0;
}
