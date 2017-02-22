
package lesson.game;

/**
 *
 * @author Less
 */
public class Knight extends PersonWithQuest {
	
	Knight(String name){
		this.name = name;
	}
	
	@Override
	public void doQuest(){
		System.out.println("Knight ="+name+"= doing '" + quest.getName() + "'");
		quest.execute();
		weapon.use();
	}
}
