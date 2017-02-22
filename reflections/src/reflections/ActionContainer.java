

package reflections;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

@Inherited
@Target(ElementType.METHOD)
@interface Action{
	
}

public class ActionContainer {
	
	private final String name = "Вася Пупкин";
	private final String color = "White";
	
	@Action
	public void printName(){
		System.out.println(name);
	}
	
	@Action
	public void printColor(){
		System.out.println(color);
	}
	
	
}
