
package oop2;


public class EnumTest {
    
    static public void test(){
        p(Days.Mon);
        p(Colors.Green);
    }
    
    
    static void p(Object x){
        System.out.println(x);
    }
}

enum Days{
    Mon, Tue, Wed, Thu, Fri, Sat, Sun
}

enum Colors{
    
    Red("#ff0000"), 
    Green("#00ff00"), 
    Blue("#0000ff");
    
    private String value;
    
    Colors(String val){
        value = val;
    }
    
    @Override
    public String toString(){
        return value;
    }
}

