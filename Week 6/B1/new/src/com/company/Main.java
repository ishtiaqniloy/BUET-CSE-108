package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);

        int i;
        double d;



        /*int ar[] = {1,2,3};

        int ar2[] = new int[]{1,2,3};

        int [][] numbers = new int[3][] ;
        //int numbers[0] = new int [1];

        for(int j=0; j<numbers.length; j++)
            numbers[j] = new int[j+1];


        for(int j=0; j<numbers.length; j++)
            for(int k =0; k< numbers[j].length; k++)
                numbers[j][k] = sc.nextInt();

        *//*for (int j[]: numbers) {
            for (int k: j) {
                k = sc.nextInt();
            }
        }*//*

        for (int j[]: numbers) {
            for (int k: j) {
                System.out.print(k+ " ");
            }
            System.out.println("");

        }*/


       /* i= sc.nextInt();
        d=sc.nextDouble();

        System.out.println(i + " " + d);*/
        String str = new String("Hello World!   ");

        System.out.println(str.length());
        System.out.println(str);
        System.out.println(str.trim());
        System.out.println(str.charAt(1));
        System.out.println(str.concat("ABCDE"));
        System.out.println(str);
        /*str = str.concat("XYZ");
        System.out.println(str);
*/
        System.out.println(str.substring(2 , 4)); //endIdx exclusive
        System.out.println(str.replace('H', 'K'));
        System.out.println(str.replaceAll("World" , "Universe"));
        System.out.println(str.indexOf("llo"));
        System.out.println(str.lastIndexOf("opq"));
        System.out.println(str.lastIndexOf("orld"));
        System.out.println(str.indexOf("llo", 3));
        System.out.println(str.equalsIgnoreCase("Hello World!"));
        System.out.println(str.equalsIgnoreCase("hello world!   "));
        System.out.println(str=="hello world!   ");
        //System.out.println(str== new String ("hello world!   "));
        System.out.println(str== new String ("Hello World!   "));//CHECKS REFERENCE

        System.out.println(str.compareToIgnoreCase("Hello World!   "));
        System.out.println(str.compareToIgnoreCase("Helbo World!   "));
        System.out.println(str.compareToIgnoreCase("Helpo World!   ")); //returns difference of ascii valou

        //split DEKHE ASHBO 

    }
}
