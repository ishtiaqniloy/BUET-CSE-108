#include <iostream>
#include <cstring>

using namespace std;

class Queue{
    char *array;
    int head;
    int tail;
    int capacity;
public:
    Queue();
    ~Queue();
    Queue(int n);
    Queue(char *p);
    void enQueue(char c);
    void enQueue(char *p);
    void deQueue();
    char front();
    char front(int pos);
};

Queue::Queue(){
    capacity=15;
    array= new char[capacity];
    head=0;
    tail=0;
}

Queue::~Queue(){
    delete []array;
}

Queue::Queue(int n){
    capacity=n;
    array= new char[capacity];
    head=0;
    tail=0;
}

Queue::Queue(char *p){
    int len;
    len=strlen(p);
    capacity=len*2;
    array= new char[capacity];
    strcpy(array, p);
    head=0;
    tail=len;
}

void Queue::enQueue( char c){
    array[tail]=c;
    tail++;
}

void Queue::enQueue(char *p){
    array[tail]=0;
    strcat(array, p);
    tail=strlen(array);
    cout<<array<<endl;
}

void Queue::deQueue(){
    head++;
}

char Queue::front(){
    return array[head];
}

char Queue::front(int pos){
    return array[head+pos];
}

int main(){
    Queue q("abcde");

    q.enQueue('k');
    //q.enQueue('b');
    cout<<q.front()<<endl;
    q.deQueue();
    cout<<q.front(0)<<endl;
    cout<<q.front(1)<<endl;
    q.enQueue("efgh");
    cout<<q.front()<<endl;
    q.deQueue();
    cout<<q.front()<<endl;




    return 0;

}
