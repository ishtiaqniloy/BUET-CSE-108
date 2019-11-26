#include <iostream>
#include <cstdio>
#include <cstring>
#include <cmath>
#include <cstdlib>

using namespace std;

class Vector{
    double x, y, z;

public:
    Vector(){
        x=0;
        y=0;
        z=0;
    }
    Vector(double x,double y,double z){
        this->x=x;
        this->y=y;
        this->z=z;
    }

    friend ostream &operator<<(ostream &stream, Vector &v);
    friend istream &operator>>(istream &stream, Vector &v);
    friend Vector operator*(int a, Vector v);


};

Vector operator*(int a, Vector v){
    v.x*=a;
    v.y*=a;
    v.z*=a;

    return v;
}

istream &operator>>(istream &stream, Vector &v){
    stream>>v.x;

    stream>>v.y;

    stream>>v.z;

    return stream;
}

ostream &operator<<(ostream &stream, Vector &v){
    stream<<v.x<<" "<<v.y<<" "<<v.z;

    return stream;
}

int main(){
    Vector v, mul;

    cin>>v;

    mul=5*v;

    cout<<mul<<endl;

}










