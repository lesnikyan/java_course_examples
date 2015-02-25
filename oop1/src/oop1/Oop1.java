/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oop1;
import actions.Movable;

/**
 *
 * @author less
 */
public class Oop1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Warlock warlock1 = new Warlock("Vasya", 100);
        warlock1.doEvilMagic();
        test(warlock1);
        warlock1.setClothes(
                3,
                new Clothes("Тапки"),
                new Clothes("Варежки"),
                new Clothes("Штаны")
        );
    }
    
    static void test(Movable obj){
        obj.move();
    }
    
    
    
}
