
package oop1;

public class Warlock extends Vizard{
    
    protected int level;
    
    {
        level = 0;
        for (int i=0; i<5; ++i){
            level +=i;
        }
    }
    
    public Warlock(String name){
        super(name);
    }
    
    /**
     * 
     * @param name - name of person
     * @param level - level of person
     */
    public Warlock(String name, int level){
        this(name);
    }
    
    @Override
    public int doEvilMagic(){
        p("Warloc attacs by magic!");
        return 20;
    }
}

