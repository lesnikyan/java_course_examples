

package lesson.game;

/**
 *
 * @author Less
 */
abstract public class PersonWithQuest implements Person {
	protected Quest quest;
	protected String name;
	protected PersonWeapon weapon;
	@Override
	abstract public void doQuest();

	public Quest getQuest() {
		return quest;
	}

	public void setQuest(Quest q) {
		quest = q;
	}

	/**
	 * @return the weapon
	 */
	public PersonWeapon getWeapon() {
		return weapon;
	}

	/**
	 * @param weapon the weapon to set
	 */
	public void setWeapon(PersonWeapon weapon) {
		this.weapon = weapon;
	}
}
