/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oop2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Scanner;

/**
 *
 * @author less
 */
public class ConsoleTest {
    public void testInput(){
        p("enter");
        //testBytes();
        testBuffer();
        //testScanner();
    }
    
    void testBytes(){
        boolean next= true;

        byte b[] = new byte[256];
        
        try {
            System.in.read(b);
        } catch (IOException ex) {

        }          
        p(new String(b, Charset.forName("UTF-8")));
    }
    
    void testBuffer(){
        BufferedReader input = new BufferedReader(
                new InputStreamReader(System.in)
        );
        String str;        
        try {
            str = input.readLine();
            System.out.println(str);
        } catch (IOException ex) {
            
        }

    }
    
    void testScanner(){
        Scanner input= new Scanner(System.in);
        String str=input.next();
        System.out.println(str);
    }

    static void p(Object x){
        System.out.println("test: " + x);
    }

}
