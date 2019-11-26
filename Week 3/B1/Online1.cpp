#include <iostream>

using namespace std;

class Queue{
    char *array;
    int head;
    int tail;
    int capacity;
public:
    Queue();
    void enQueue(char c);
    void deQueue();
    char front();
};

Queue::Queue(){
    capacity=15;
    array= new char[capacity];
    head=0;
    tail=0;
}

void Queue::enQueue( char c){
    array[tail]=c;
    tail++;
}

void Queue::deQueue(){
    head++;
}

char Queue::front(){
    return array[head];
}

int main(){
    Queue q;

    q.enQueue('a');
    q.enQueue('b');
    cout<<q.front()<<endl;
    q.deQueue();
    cout<<q.front()<<endl;
    q.enQueue('x');
    cout<<q.front()<<endl;
    q.deQueue();
    cout<<q.front()<<endl;




    return 0;

}
