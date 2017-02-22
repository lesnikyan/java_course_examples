/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pets;
import actions.Movable;

/**
 *
 * @author less
 */
public class Pet implements Movable {
    public void move(){
        p("Pet moves");
    }
    
    protected void p(Object x){
        System.out.println(x);
    }
}
