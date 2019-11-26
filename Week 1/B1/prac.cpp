#include <iostream>

using namespace std;

class Box{
    private:
        double x1,y1,z1,x2,y2,z2;

    public:
        double volume();
        double length();
        double height();
        double width();
        void setPoint1(double x, double y, double z);
        void setPoint2(double x, double y, double z);
        bool isContain(double x, double y, double z);

};

int  main(){
    double x,y,z;

    class Box b;

    class Box c;

    cout<<"Coordinates of 1st point:\n";
    cin>>x>>y>>z;
    b.setPoint1(x,y,z);

    cout<<"Coordinates of 2nd point:\n";
    cin>>x>>y>>z;
    b.setPoint2(x,y,z);

    cout<<"Volume of the box= "<<b.volume()<<"\n";

    cout<<"Coordinates of test point:\n";
    cin>>x>>y>>z;
    if( b.isContain(x,y,z) )
        cout<<"Inside box";
    else
        cout<<"Outside box";


}

void Box::setPoint1(double x, double y, double z){
    x1=x;
    y1=y;
    z1=z;

}

void Box::setPoint2(double x, double y, double z){
    x2=x;
    y2=y;
    z2=z;

}

double Box::height(){
    double r;
    r=z1-z2;
    r=r>0?r:-r;
    return r;
}

double Box::length(){
    double r;
    r=x1-x2;
    r=r>0?r:-r;
    return r;
}

double Box::width(){
    double r;
    r=y1-y2;
    r=r>0?r:-r;
    return r;
}

double Box::volume(){
    return height()*length()*width();
}

bool Box::isContain(double x, double y, double z){
    if ( ( (x>=x1&&x<=x2)|| (x>=x2&&x<=x1) ) && ( (y>=y1&&y<=y2)|| (y>=y2&&y<=y1) ) && ( (z>=z1&&z<=z2)|| (z>=z2&&z<=z1) ) )
        return true;
    else
        return false;

}
