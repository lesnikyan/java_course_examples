
package oop1;
import pets.*;

public class Vizard extends Person {
    
    Golem pet;
    
    public Vizard(String name){
        this.name = name;
    }
    
    public int doEvilMagic(){
        p("Vizard attacs by magic!");
        return 10;
    }
    
    final String typeOfWearing = "Clothes";
    
    protected final String getName(){
        return name;
    }
    
    @Override
    public void move(){
        p("Vizadr going to ...");
    }
    
}
