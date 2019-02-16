#include<iostream>
#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<cstring>
#include<string>

using namespace std;

//Do the tasks as such the output is similar to the given execl
//complete the following class

class Cart;
class Phones;
class Foods;

double getTaxFromType(int a);

class Product{
    private:
        int id;
        string name;
        static int counter;

    protected:
        double price;
        Product *products[100];
        int productNo;
        int type;       //type=1 -> phone , type=2 -> food

    public:
        Product(string n, double p){
                type=0;
                price = p;
                name = n;
                productNo=0;
                counter++;
                id=counter;
        }
        int getProductNo(){
            return productNo;
        }
        double getPrice(){
            return price;
        }

        string getname() {
            return name;
        }
        int getId(){
            return id;
        }

        void showFreeProducts(){
            double pr, t, totalPrice=0;
            double productPrice[productNo];

            if(productNo>0){
                for(int i=0; i<productNo; i++){
                    pr=products[i]->getPrice();

                    t=getTaxFromType(products[i]->getType());

                    pr+=pr*t/100;

                    productPrice[i]=pr;
                    totalPrice+=pr;

                }

                cout<<"Free : "<<totalPrice<<endl;

            }



            for(int j=0; j<productNo; j++){
                cout<<"-->"<<products[j]->getname()<<"("<<products[j]->getId()<<")"<<" : "<<productPrice[j]<<endl;

            }
        }

        int getType(){
            return type;
        }

};

int Product::counter=0;

//override necessary methods
class Phones:public Product{
    private:
        static double tax;

    public:
       static void setTax(double t) {
           tax = t;
        }

        static double getTax(){
            return tax;
        }

        Phones(string n, double i) :  Product(n,i){
            type=1;
        }

        void addFreeProduct(Product *p){
            products[productNo]=p;
            productNo++;
        }


};

double Phones::tax= 0;

//override methods
class Foods:public Product{

    private:
        static double tax;

    public:
       static void setTax(double t) {
           tax = t;

       }

        static double getTax(){
            return tax;
        }

        Foods(string n, double i) : Product(n,i){
            type=2;
        }

        void addFreeProduct(Product *p){
            products[productNo]=p;
            productNo++;
        }


};

double Foods::tax= 0;


class Cart{
    double totalPrice;
    Product *products[100];
    int itemNo;

public:
    Cart(){
        totalPrice=0;
        itemNo = 0;
    }
    void addToCart(Product *p){
        products[itemNo]=p;
        totalPrice+=products[itemNo]->getPrice();
        itemNo++;
    }

    void showCartDetails(){
        int pr;
        for(int i=0; i<itemNo; i++){
            pr=products[i]->getPrice();

            if(products[i]->getType()==1)
                pr+=pr*Phones::getTax()/100;
            else if(products[i]->getType()==2)
                pr+=pr*Foods::getTax()/100;

            cout<<"------------------------"<<endl;
            cout<<products[i]->getname()<<"("<<products[i]->getId()<<")"<<" : "<<pr<<endl;
            products[i]->showFreeProducts();

        }

    }

};

double getTaxFromType(int a){
    if(a==1)
        return Phones::getTax();
    else if(a==2)
        return Foods::getTax();
    else
        return 0;
}

int main()
{
    Phones::setTax(15); // set  15% VAT on phones
    Foods::setTax(5);  // set 5% VAT on Foods
    Cart myCart;

    Foods mango("Rajshahir mango",500);
    Foods pithaPuli("Chitoi pitha",200);
    Foods hilsha("Ilish",1000);

    Product vrset("Virtual Reality Headset",10000);

    Phones samsungGalaxy("Samsung Galaxy S7",75000);
    Phones nokia("Nokia 1100",2000);

    samsungGalaxy.addFreeProduct(&vrset);
    samsungGalaxy.addFreeProduct(&hilsha);

	//addToCart add product to Cart
    myCart.addToCart(&samsungGalaxy);
    myCart.addToCart(&mango);
    myCart.addToCart(&pithaPuli);
    myCart.addToCart(&nokia);


//showCartDetails show products and their price
    myCart.showCartDetails();

    cout<<endl<<"**********After Tax Change*********"<<endl<<endl;

    Phones::setTax(10);
    Foods::setTax(7);

    myCart.showCartDetails();




    return 0;
}
