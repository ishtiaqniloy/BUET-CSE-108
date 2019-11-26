
/*Offline week-5 1505114*/

#include<iostream>
#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<cstring>
#include<string>

using namespace std;

///Do the tasks as such the output is similar to the given execl
///complete the following class

class Product{
    private:
        int id;
        string name;
        static int counter;
    protected:
        double price;
        Product *products[100];
        int productNo;

    public:
        Product(string n , double p){
            name=n;
            price = p;
            counter ++;
            id=0;
            productNo= counter;
        }

        double getprice(){return price;}
        double getprice(int x){return products[x]->price;}
        string getname(){return name;}
        string getname(int x){return products[x]->name;}
        int getproductno(){return productNo;}
        int getproductno(int x){return products[x]->productNo;}
        void setid(int i){id=i;}
        int getid(){return id;}
        virtual double getTax(){return 0;}
        double getTax(int x){return products[x]->getTax();}



};
int Product::counter;

///override necessary methods
class Phones:public Product{
    private:
        static double tax;
        static int newid;


    public:

        Phones(string n , double p):Product(n, p){newid=0;}
        double getTax(){return tax;}
        static void setTax(double t){tax=t;}

        void addFreeProduct(Product *p)
        {
//            int newid = this->getid();
            products[newid]=p;

             newid++;

            cout<<"-->"<<products[newid]->getname(newid)<<endl;

            this->setid(newid);

        }

};
int Phones::newid=0;

///override methods
class Foods:public Product{

private:
        static double tax;
public:
    Foods(string n , double p):Product(n, p){}

    static void setTax(double t){tax=t;}
    double getTax(){ return tax;}

};
double Foods::tax=0;
double Phones::tax=0;

class Cart{
    double totalPrice;
    Product *products[100];
    int itemNo;

public:
    Cart()
    {
        itemNo=0;
        totalPrice=0;
    }
    void addToCart(Product *p)
    {
        products[itemNo]=p;
        itemNo++;
    }
    void showCartDetails()
    {
        int tax=0;
        totalPrice =0;
        for(int i=0;i<itemNo;i++)
        {
            tax = (products[i]->getTax() * products[i]->getprice())/100;
            tax += products[i]->getprice() ;


            cout<<"---------------------------"<<endl;
            cout<<products[i]->getname()<<"("<<products[i]->getproductno()<<") : "<<tax<<endl;

//            cout<<"-->"<<products[0]->getname(0)<<"("<<products[0]->getproductno(0)<<") : "<<endl;
            for(int j=0;j<products[i]->getid() -1 ;j++)
            {
                tax = (products[j]->getTax(j) * products[j]->getprice(j))/100;
                tax += products[j]->getprice(j) ;

                totalPrice += tax;
            }
            if(products[i]->getid()){
            cout<<"Free : "<<totalPrice<<endl;
            }
            for(int j=0;j<products[i]->getid() -1;j++)
            {

                cout<<"-->"<<products[j]->getname(j)<<"("<<products[j]->getproductno(j)<<") : "<<products[j]->getprice(j)<<endl;
            }


        }

    }

};

int main()
{
    Phones::setTax(15); /// set  15% VAT on phones
    Foods::setTax(5);  /// set 5% VAT on Foods
//    Product::setTax(0);
    Cart myCart;

    Foods mango("Rajshahir mango",500);
    Foods pithaPuli("Chitoi pitha",200);
    Foods hilsha("Ilish",1000);
    Product vrset("Virtual Reality Headset",10000);

    Phones samsungGalaxy("Samsung Galaxy S7",75000);
    Phones nokia("Nokia 1100",2000);


    samsungGalaxy.addFreeProduct(&hilsha);
    samsungGalaxy.addFreeProduct(&vrset);
    //samsungGalaxy.addFreeProduct(&hilsha);

	///addToCart add product to Cart
    myCart.addToCart(&samsungGalaxy);
    myCart.addToCart(&mango);
    myCart.addToCart(&pithaPuli);
    myCart.addToCart(&nokia);
    myCart.addToCart(&vrset);
    myCart.addToCart(&hilsha);


/// showCartDetails show products and their price
    myCart.showCartDetails();

    cout<<endl<<"**********After Tax Change*********"<<endl<<endl;

    Phones::setTax(10); //reset vat
    Foods::setTax(7);

    myCart.showCartDetails(); //show new details based on calculated VAT




    return 0;
}
