#include<iostream>
#include<string>
using namespace std;

//base class
class Person
{
public:
	Person()
	{
		cout  << "Default constructor of base class called" << endl;
	}
	Person(string lName, int year)
	{
		cout  << "Parameterized constructor of base class called" << endl;
		lastName = lName;
		yearOfBirth = year;
	}
	string lastName;
	int yearOfBirth;
};

//derived class
class Student :public Person
{
public:
	Student()
	{
		cout  << "Default constructor of Derived class called" << endl;
	}
	Student(string lName, int year, string univer)
	{
		cout  << "Parameterized constructor of Derived class called" << endl;
		university  = univer;
	}
	string university;
};


int main()
{
    Student student1; //Using default constructor of Student class
    Student student2("John", 1990, "London School of  Economics"); //calling parameterized
}
