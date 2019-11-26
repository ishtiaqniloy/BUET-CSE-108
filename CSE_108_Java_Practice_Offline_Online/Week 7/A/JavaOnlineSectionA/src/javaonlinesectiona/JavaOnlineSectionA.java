/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaonlinesectiona;
import java.io.BufferedReader;
import java.io.FileReader;

public class JavaOnlineSectionA {


    public static void main(String[] args) {
        BufferedReader br;
        StringBuilder str= new StringBuilder();
        try {
            br = new BufferedReader(new FileReader("input.txt"));
            while(true){
                String s = br.readLine();
                if(s == null) break;
                str.append(s);
                str.append("\n");
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        String s = new String (str);
        
        //your task start here 
        int line_count = 0;
        int sentence_count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '\n'){
                line_count++;
            }else if(ch == '.'){
                sentence_count++;
            }
            
        }
        System.out.println("Line count= " + line_count);
        System.out.println("Sentence Count= "+ sentence_count);
        
        
    }
    
}
