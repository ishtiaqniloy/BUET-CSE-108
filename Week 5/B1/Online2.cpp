#include <iostream>
#include <cstdio>
#include <cstring>
#include <cmath>
#include <cstdlib>

using namespace std;


class Cricketer{
    int matchPlayed;
    char name[100];
    int age;

public:
    Cricketer(char *s,int a,int m){
    strcpy(name, s);
    age=a;
    matchPlayed=m;

}
    char *getName(){
        return name;
    }
    int getMatchPlayed(){
        return matchPlayed;
    }

};



class Batsman: virtual public Cricketer{
    int runScored;
public:
    Batsman(char *s,int age,int matchPlayed,int runScorred):Cricketer(s, age,matchPlayed){
        runScored=runScorred;
    }

    double computeBattingAverage(){
        return runScored*1.0/getMatchPlayed();
    }


};

class Bowler: virtual public Cricketer{
    int wicketsTaken;

public:
    Bowler(char *s,int age,int matchPlayed,int wickets):Cricketer(s, age,matchPlayed){
        wicketsTaken=wickets;
    }

    double computeBowlingAverage(){
        return wicketsTaken*1.0/getMatchPlayed();
    }




};

class AllRounder: public Batsman, public Bowler{
public:
    AllRounder(char *s,int age,int matchPlayed,int runScorred,int wickets) : Cricketer(s, age, matchPlayed), Batsman(s, age, matchPlayed, runScorred  ) , Bowler(s, age, matchPlayed, wickets  ){

    }


};

int main(){

    Batsman B1("abc", 2, 2, 5);
    Bowler B2("def", 10, 5, 67);
    AllRounder shakib("Shakib",28,200,5000,400);

    double d;

    d=B1.computeBattingAverage();

    cout<<B1.getName()<<" "<<B1.getMatchPlayed()<<" "<<d<<endl;

    d=B2.computeBowlingAverage();

    cout<<B2.getName()<<" "<<B2.getMatchPlayed()<<" "<<d<<endl;

    cout<<shakib.getName()<<" "<<shakib.getMatchPlayed()<<" "<<shakib.computeBattingAverage()<<" "<<shakib.computeBowlingAverage()<<endl;

    return 0;
}
