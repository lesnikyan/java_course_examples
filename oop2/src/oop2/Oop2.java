/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oop2;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author less
 */
public class Oop2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        General gen = new General();
        gen.test();
        General.SubLevel1 sub1 = gen.new SubLevel1();
        p("main method sub1 test: " + sub1.test());
        gen.testLocal();
        p("Genersl abstr val = " + General.abstractValue);
        
        // Labels
        testLabel();
        
        // Console test
        
        ConsoleTest con = new ConsoleTest();
        // con.testInput();
        
        p("Enum tests");
        EnumTest.test();
    }
    
    static void p(Object x){
        System.out.println(x);
    }
    
    static void testLabel(){
        int [] nums = {1,2,3,4,5};
        p("Label test");
        int c = 0;
        label1:{
            p("start of iter");
            for(int i = 0 ; i < 3;++i){
                for(int num: nums){
                    c++;
                    p(num);
                    if(c > 30)
                        break;
                    if(num == 3){
                        break label1;
                    }
                }
            }
        }
    }
    
}
