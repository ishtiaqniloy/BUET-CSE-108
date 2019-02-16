#include <iostream>

using namespace std;


class Stack {
    int *p, head;


    public:

    Stack();
    ~Stack();
    void push(int x);
    void pop();
    int peek();
    int getSize();
    bool isEmpty();

};


void dumpStack(Stack st);
Stack copyStack(Stack st);

int main(){

    Stack st, st2;

    st.push(10);
    st.push(2);
    st.push(5);

    cout<<"size = "<<st.getSize()<<endl;
    cout<<"last element "<<st.peek()<<endl;

    st.pop();
    cout<<"last element "<<st.peek()<<endl;

    st.push(12);



    st2=copyStack(st);
    cout<<"First stack:"<<endl;
    st.push(20);
    dumpStack(st);
    cout<<"copied stack:"<<endl;
    st2.push(15);
    dumpStack(st2);
    dumpStack(st2);


    return 0;
}

Stack::Stack(){
    p= new int[10];
    head=0;
    //cout<<"Executing Constructor"<<endl;
}
Stack::~Stack(){
    delete p;
    //cout<<"Executing destructor"<<endl;
}

void Stack::push(int x){
    p[head]=x;
    head++;


}

void Stack::pop(){
    head--;

}

int Stack::peek(){
    return p[head-1];

}

int Stack::getSize(){

    return head;

}

bool Stack::isEmpty(){
    if(head)
        return false;
    else
        return true;


}

void dumpStack(Stack st){
    while(!st.isEmpty()){
        cout<<st.peek()<<endl;
        st.pop();
    }

}


Stack copyStack(Stack st){
    Stack temp, temp2;
    /*int size=st.getSize();


    for(int i=0; i<size; i++){
        temp.push(st.peek());
        st.pop();
    }
    for(int i=0; i<size; i++){
        temp2.push(temp.peek());
        temp.pop();
    }*/
    temp2=st;

    return temp2;

}
