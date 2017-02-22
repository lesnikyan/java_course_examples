/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generics;

/**
 *
 * @author Less
 */
public class Generics {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		// унификация кода
		// безопасность типов
		
		// simple
		GenParam<Integer> igen = new GenParam<Integer>(12);
		GenParam<Double> dgen = new GenParam<Double>(12.0);
		GenParam<Double> dgen2 = new GenParam<Double>(20.0);
		
		p(igen.equal(dgen));
		p(igen.equal(dgen2));
		
		// <T,V>
		DualGeneric dg;
		
		// <T extends MyBaseClassOrInterface>
		ExtGeneric exg;
		MultiExtGeneric mext;
		
		// обобщенные параметры:
		// <? extends MyBaseClass>
		Aext aext = new Aext();
		Bext bext = new Bext();
		
		// 
		ExtParam<Aext> axparam = new ExtParam<Aext>(aext);
		ExtParam<Bext> bxparam = new ExtParam<Bext>(bext);
		
		// приведение типов:
		ExtParam<Bext> cxparam = (ExtParam<Bext>) bxparam;
		
		// проверка типа
		axparam.showStaus(bxparam);
		p("instanceof: " + (axparam instanceof ExtParam<?>));
		
		// <? super MyInheritedClass>
		
		// diamond operator
		// new GenClass<>()
		ExtParam<Bext> dxparam = new ExtParam<>(bext);
		
		// <T, V> int myMethod(T, V) {return 5;}
		Integer x = 10;
		Integer[] arr = new Integer[]{1, 5, 10, 15};
		p("isIn: " + isIn(x, arr));
		
		// erasure - очистка типа во время компиляции.
		
		// ограничения:
		// 1. неоднозначность типа после очистки. 
		// нельзя объявить два метода с одинаковым именем (перегрузить)
		// и обобщенными типами в аналогичной сигнатуре:
		// void set(T x){}
		// void set (V x){}
		
		// 2. нельзя создать экземпляр обобщенного типа 
		// T x = new T();
		
		// 3. нельхя использовать обобщенные типы в статических методах и полях.
		
		// 4. нельзя создать массив обобщенного типа
		// T[] arr = new T[10];
		// нельзя создать массив ссылок обобщенного типа
		// MyGen<Integer> arr = new MyGen<Integer>[10];
		
		// 5. нельзя расширить Throwable обобщенным типом.
	}
	
	static <N, Q extends N> boolean isIn(N x, Q[] arr){
		for(int i = 0; i < arr.length; ++i){
			if( x == arr[i])
				return true;
		}
		return false;
	}
	
	static void p(Object x){
		System.out.println(x);
	}
}
