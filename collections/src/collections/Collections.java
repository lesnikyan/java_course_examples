/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collections;
import java.util.*;

/**
 *
 * @author Less
 */
public class Collections {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		vector();
		hashtable();
		enums();
		stack();
		lambda();
		aboutArrays(); // help info in comments
		stream();
		// ArrayList
		// LinkedList
		// HashMap
		// WeakHashMap
		// LinkedHashMap
		// TreeMap
		// HashSet
		// LinkedHashSet
	} 

	static void testString(){
		String commands = " add Vasya 123456 ".trim();
		p("string has " +(commands.contains("add") ? "":"no") + " command");
		String[] commandsList = commands.split("\\s");
		if(commandsList[0].equalsIgnoreCase("add")){
				p("do command add ...");
		}
	}

	static void lambda(){
		ArrayList<String> names = new ArrayList(Arrays.asList("Вася", "Вова", "Вера", "Вадик", "Виталик", "Венеамин"));
		names.forEach((name) ->  p("lambda out: " + name));
	}

	static void vector(){
		p("-- Vector --");
		//Common [] arr = {new Common("AAA"),new Common("BBB"),new Common("CCC"),};
		Vector<Common> vector = new Vector<Common>(5);
		vector.add(new Common("AAA"));
		vector.add(new Common("BBB"));
		vector.add(new Common("CCC"));

		// add, addAll, get, 

		p("Vector capacity: " + vector.capacity());

		for(Common val: vector){
			p("Vector: " + val);
		}

				vector.addAll(Arrays.asList(new Common("DDD"), new Common("EEE"), new Common("FFF")));

		for(int i=0; i<vector.size(); ++i){
			p("Vector.get: " + vector.get(i));
		}

				Iterator it = vector.iterator();
	}

	static void hashtable(){
		p("-- HashTable --");
		Hashtable<String, Integer> data = new Hashtable<String, Integer>(20);
		data.put("Vasya", 123546);
		data.put("Valya", 10000);
		data.put("Kolya", 20000);
		data.put("Masha", 55555);
		// putAll (Map)
		// get
		// size
		// capacity
		// values
		// keySet

		for(String key: data.keySet()){
			p("Hashtable: " + key + " = " + data.get(key));
		}

				HashMap hmap = new HashMap();
	}

	static void stack(){
		Stack<String> stack = new Stack<String>();
		stack.push("Stack test 1");
		stack.push("Stack test 2");
		stack.push("Stack test 3");
		p("pop from: " + stack.pop());
		stack.push("Stack test 4");

		while(!stack.empty()){
			p("Stack.pop while: " + stack.pop());
		}
	}

	static void enums(){
		p("-- Enum --");
		for(Lexems val: Lexems.values()){
			p( "lex:" + val);
		}
		p("Lexval = " + Lexems.valueOf("CCC"));
				//p(Lexems.valueOf("QWERTY"));
	}

	static void aboutArrays(){
		/*
		asList — метод, который формирует оболочку List для переданного массива.
		binarySearch — метод выполняет бинарный поиск по массиву, переданному первым параметром, значения из второго параметра.
		copyOf — копирует переданный массив. Вторым параметром передаём количество элементов, которое хотим получить в новом массиве.
		copyOfRange — то же, что и copyOf, но передаём индекс начала и индекс конца области копирования.
		deepEquals — сравнивает два массива, учитывая, что они могут быть многомерными
		deepHashCode — высчитывает хэш массива, учитывая, что они могут быть многомерными
		deepToString — преобразует массив в строку, учитывая, что они могут быть многомерными
		equals — сравниват массивы
		fill — заполняет массив одним и тем же значением, переданным вторым параметром
		hashCode — считает хэш
		sort — сортирует массив
		toString — преобразует массив в строку
		*/

		/*
		interface Queue
		http://www.seostella.com/ru/article/2012/08/09/kollekcii-collections-v-java-queue.html
		*/
	}
	
	static void stream(){
		p("Stream of collection:");
		class User {
			private int age;
			User(int age){
				this.age = age;
			}
			int getAge(){
				return age;
			}
		}
		
		List<Integer> nums = new LinkedList<>();
		int numCount = 100;
		Random r = new Random();
		
		for(int i=0; i< numCount; ++i){
			nums.add(r.nextInt(100));
		}
		
		int sumEven20_60 = nums.stream()
				.filter( x -> (x >= 20 && x <= 60 && x % 2 == 0 && p(x, true)) )
				.map(x -> x * 5)
				.reduce(0, (x, y) -> x + y );
		p("From 20 to 60 sum multiplied of 5 = " + sumEven20_60);
	}

	static void p(Object x){
		System.out.println(x);
	}

	static boolean p(Object x, boolean d){
		System.out.println(x);
		return d;
	}

	static String ints2s(int [] x){
			return Arrays.toString(x);
	} 

}


enum Lexems{
	AAA, BBB, CCC, DDD;
}

