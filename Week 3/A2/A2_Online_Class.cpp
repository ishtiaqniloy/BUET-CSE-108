#include <iostream>
#include <cmath>


using namespace std;

class Vector{
    double x;
    double y;
    double z;
    double m;

public:
    Vector();
    Vector(double a, double b, double c);

    double getX(){
        return x;
    }
    double getY(){
        return y;
    }
    double getZ(){
        return z;
    }
    double getM(){
        return m;
    }



};

Vector::Vector(){
    x=0;
    y=0;
    z=0;
    m=0;
}

Vector::Vector(double a, double b, double c){
    x=a;
    y=b;
    z=c;

    m=sqrt(x*x+y*y+z*z);

}

Vector addVectors(Vector u, Vector v){
    Vector r(u.getX()+v.getX(),u.getY()+v.getY(),u.getZ()+v.getZ());
    return r;

}

Vector subVectors(Vector u, Vector v){
    Vector r(u.getX()-v.getX(),u.getY()-v.getY(),u.getZ()-v.getZ());
    return r;

}

double dotProduct( Vector u, Vector v){
    double r;
    r=u.getX()*v.getX()+u.getY()*v.getY()+u.getZ()*v.getZ();

    return r;
}

Vector crossProduct( Vector u, Vector v){
    double u1=u.getX(), u2=u.getY(), u3=u.getZ(), v1=v.getX(), v2=v.getY(), v3=v.getZ();
    Vector r(u2*v3 - u3*v2, u3*v1 - u1*v3, u1*v2 - u2*v1);
    return r;

}


int main(){
    Vector a(1,2,3), b(4,5,6), add, sub, cross;

    double dot;

    add=addVectors(a,b);

    cout<<add.getX()<<","<<add.getY()<<","<<add.getZ()<<endl;

    sub=subVectors(a,b);

    cout<<sub.getX()<<","<<sub.getY()<<","<<sub.getZ()<<endl;

    dot=dotProduct(a,b);

    cout<<dot<<endl;

    cross=crossProduct(a,b);

    cout<<cross.getX()<<","<<cross.getY()<<","<<cross.getZ()<<endl;


    return 0;
}
