/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reflections;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static reflections.Generics.p;


// объявление аннотации
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
	String name();
	int val();
}

// упрощенная передача атрибута 
@Retention(RetentionPolicy.RUNTIME)
@interface SimpleAnnotation {
	String value();
}

// упрощенная передача атрибута + атрибут с умолчанием
@Retention(RetentionPolicy.RUNTIME)
@interface SimpleComplexAnnotation {
	String value();
	int count() default 100;
}

// массив в качестве значения атрибута
@Retention(RetentionPolicy.RUNTIME)
@interface NamesArrayAnnotation{
	String[] value() default {"Вася Пупкин"};
}

// аннотация в качестве атритута аннотации
@Retention(RetentionPolicy.RUNTIME)
@interface Knowledge{
	NamesArrayAnnotation value();
}

@Retention(RetentionPolicy.RUNTIME)
@interface RuntimeCall{
	
}

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) // политика удержания аннотации
@interface MyClass{
	String value();
}

@MyClass("Demo")
public class AnnotationDemo {
	
	// аннотирование метода
	@MyAnnotation(name = "Аннотация метода", val = 123)
	public void mySpecialMethod(String title, int count){
		
	}
	
	@SimpleAnnotation("Упрощенная аннотация")
	public void simpleSpecialMethod(){
		
	}
	
	@NamesArrayAnnotation({"Юрий Гагарин", "Нил Армстронг"})
	public void names(){
		
	}
	
	@Knowledge(
		@NamesArrayAnnotation({"Сократ", "Платон", "Аристотель"})
	)
	public void complicated(){
	
	}
	
	@RuntimeCall
	public void run(String someVal){
		p("Demo.run -> " + someVal);
	}
}


/*
Допустимые типы атрибутов аннотации:
	примитивы
	String
	Class или «any parameterized invocation of Class» (Class<?>)
	enum
	annotation
	массив элементов любого из вышеперечисленных типов
*/

/*
	ElementType.TYPE_PARAMETER — параметризованный тип(MyClass<T>)
	ElementType.TYPE_USE — принимает тип параметра
*/

/*
More info: http://habrahabr.ru/company/golovachcourses/blog/217595/
*/