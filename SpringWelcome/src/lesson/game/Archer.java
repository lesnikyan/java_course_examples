
package lesson.game;

/**
 *
 * @author Less
 */
public class Archer extends PersonWithQuest {
	@Override
	public void doQuest(){
		System.out.println("Archer doing " + quest.getName());
		quest.execute();
		weapon.use();
	}
}
