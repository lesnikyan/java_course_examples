
package lesson.game;

/**
 *
 * @author Less
 */
public interface Quest {
	public void init();
	public String getName();
	public void setName(String name);
	public void execute();
}
