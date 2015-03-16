
package lesson.simple;

/**
 *
 * @author Less
 */
public class SimpleBean {
	private String str;

	/**
	 * @return the str
	 */
	public String getStr() {
		return str;
	}

	/**
	 * @param str the str to set
	 */
	public void setStr(String str) {
		this.str = str;
	}
	
	public String getMessage(){
		return String.format("Message: %s", str);
	}
	
}
