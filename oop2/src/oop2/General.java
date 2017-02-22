
package oop2;

public class General {
    
    private String name = "Super General Name";
    
    public static String abstractValue;
    
    static {
        for(int i=0; i< 10; ++i){
            abstractValue += " " + i;
        }
    }
    
    public class SubLevel1{
        
        class Sub2Level2{
            String subName(){
                return "The Vasya Level 2";
            }
        }
        
        String test(){
            return "Sub Level 1 of " + General.this.getName();
        }
        
    }
    
    public String getName(){
        return name;
    }
    
    public void test(){
        SubLevel1 sub1 = new SubLevel1();
        p("General test");
        p(sub1.test());
    }
    
    public void testLocal(){
        final String s = "Tesing on Local class";
        
        class Local{
            void localTest(){
                p(s);
            }
        }
        
        Local loc = new Local();
        loc.localTest();
    }
    
    static void p(Object x){
        System.out.println(x);
    }
}
