
package oop1;
import actions.Movable;

public abstract class Person implements Movable{
    protected String name;
    protected Clothes clothes[] = new Clothes[10] ;
    
    protected int protection = 10;
    
    protected void p(Object x){
        System.out.println(x);
    }
    
    public int getDamage(int damage){
        return damage;
    }
    
    public int getDamage(Damage dam){
        int res = dam.value - protection;
        return res;
    }
    
    public void setClothes(int x, Clothes... set){
        p("num of clothes = " + set.length);
        
        for(Clothes element: set){
            element.setName(element.getName() + " of " + name);
            p(element);
        }
    }
    
}

