/*

JAXB - Java Architecture for XML Binding

 */

package serialize;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author Less
 */
public class XmlSerialize {
	
	private static final Logger logger = Logger.getLogger(XmlSerialize.class.getName());
	
	Path path;
	JAXBContext marshContext;
	
	public XmlSerialize(String fileName){
		path = Paths.get(fileName);
		try {
			// Create marshalling context
			marshContext = JAXBContext.newInstance(UserCalendar.class.getPackage().getName());
		} catch (JAXBException ex) {
			logger.log(Level.SEVERE, "Something wrong.", ex);
		}
			
	}
	
	/**
	 * run both tests
	 */
	public void test(){
		try {
			marshalling();
			unmarshalling();
		} catch (JAXBException ex) {
			Logger.getLogger(XmlSerialize.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(XmlSerialize.class.getName()).log(Level.SEVERE, null, ex);
		} catch (XMLStreamException ex) {
			Logger.getLogger(XmlSerialize.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * test serialize
	 * @throws JAXBException
	 * @throws IOException
	 * @throws XMLStreamException 
	 */
	public void marshalling() throws JAXBException, IOException, XMLStreamException{
		
		UserCalendar calendar = testData(); // данные для сериализации
		
		// выходной файловый поток
		XMLStreamWriter writer = XMLOutputFactory.newFactory()
				.createXMLStreamWriter(Files.newOutputStream(path, StandardOpenOption.CREATE));
		
		// маршализатор
		Marshaller mr = marshContext.createMarshaller();
		//устанавливаются свойства маршализатора
		mr.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // свойство: форматировать
		mr.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); // свойство: кодировка документа
		
		// маршализация в файловый поток
		mr.marshal(calendar, writer); // маршализация в поток xml файла
		writer.close();
		
		// маршализация в строку
		StringWriter strw = new StringWriter();
		mr.marshal(calendar, strw);
		p(strw);
	}
	
	/**
	 * test unserialize
	 * @throws JAXBException
	 * @throws IOException
	 * @throws XMLStreamException 
	 */
	public void unmarshalling() throws JAXBException, IOException, XMLStreamException{
		// входной поток
		XMLStreamReader reader = XMLInputFactory.newFactory()
				.createXMLStreamReader(Files.newInputStream(path, StandardOpenOption.READ));
		// демаршализатор
		Unmarshaller umr = marshContext.createUnmarshaller();
		
		// демаршализация документа из файла
		UserCalendar calendar = (UserCalendar) umr.unmarshal(reader);
		
		// вывод демаршализованного объекта в консоль
		ArrayList<XmlStorableUser> users = calendar.getUsers();
		for(XmlStorableUser user: users){
			p(user.getInfoText());
			p("---");
			p(user.getActionsContents());
			p("\n\n");
		}
	}
	
	
	/**
	 * some complicated data for tests
	 * @return 
	 */
	private UserCalendar testData(){
		
		// Create complicated object set
		UserCalendar calendar = new UserCalendar();
		
		XmlStorableUser user = new XmlStorableUser(
			"Vasya", "Pupkin", 
			"King-Kong-Stadt, Bim-Bom Strasse, 11/22", 
			20, 8.0, "");
		user.addAction(new Action(Action.createDate(2014, 8, 1), 
				"School starting", "New school year begins."));
		user.addAction(new Action(Action.createDate(2014, 9, 15), 
				"Alises birthday", "Long and boring party in cheap club."));
		user.addAction(new Action(Action.createDate(2014, 11, 22), 
				"Stag-party", "Another boring action... without girls."));
		calendar.addUser(user);
		
		XmlStorableUser user2 = new XmlStorableUser(
			"Kolya", "Bubkin", 
			"Cowboy City, Parkanadtsyat-street, 4", 
			22, 7.5, "");
		user2.addAction(new Action(Action.createDate(2014, 8, 1), 
				"School starting", "New school year begins."));
		user2.addAction(new Action(Action.createDate(2014, 9, 15), 
				"Alises birthday", "Long and boring party in cheap club."));
		user2.addAction(new Action(Action.createDate(2014, 11, 22), 
				"Stag-party", "Another boring action... without girls."));
		calendar.addUser(user2);
		
		return calendar;
	}
	
	public static void p(Object x){
		System.out.println(x);
	}
	
}

/*
XmlAccessType (что маршализировать)
	.NONE - только аннотированные
	.FIELD - нестатические и не-transient поля
	.PROPERTY - все геттер/сеттер пары, и поля отмеченные аннотацией
	.PUBLIC - публичные геттер/сеттер пары и поля, и отмеченные аннотацией
*/

/**
 * Root object - объектный контейнер над коллекцией юзеров
 * @author Less
 */
@XmlRootElement // только для объекта верхнего уровня
@XmlAccessorType(XmlAccessType.FIELD) // тип доступа к полям и геттерам класса
@XmlType(name = "CalendarRoot") // указывает что класс будет сериализоваться, устанавливает имя xml-элемента
class UserCalendar {
	
	@XmlElement // аннотация XML элемента. всегда сериализуется.
	private ArrayList<XmlStorableUser> users = new ArrayList<>();
	
	UserCalendar(){}
	
	public void addUser(XmlStorableUser user){
		users.add(user);
	}
	
	public ArrayList<XmlStorableUser> getUsers(){
		return users;
	}
	
}

/**
 * Класс юзера
 * @author Less
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User")
class XmlStorableUser {
	private String name;
	private String surname;
	private String adress;
	
	@XmlAttribute(required = true) // сериализует в атрибут родительского элемента, обязателен
	private int age;
	
	@XmlAttribute(required = false) // сериализует в атрибут родительского элемента, не обязателен
	private double rating;	// social rating
	
	
	@XmlAttribute(required = true, name="global-id") // устанавливает имя, отличное от имени поля класса
	private String gid;		// global ID
	
	@XmlElement(name="action") // устанавливает имя элемента
	@XmlElementWrapper(name="action-list") // устанавливает элемент-контейнер для коллекции, или массива
	ArrayList<Action> actions = new ArrayList<>();
	
	XmlStorableUser(){
		this("", "", "", 0, 0.0, "");
	}
	
	XmlStorableUser(String name, String surname, String adress, int age, double rating, String gid){
		this.name = name;
		this.surname = surname;
		this.adress = adress;
		this.age = age;
		this.rating = rating;
		this.gid = gid;
	}
	
	public void addAction(Action action){
		actions.add(action);
	}
	
	public void semoveAction(Action action){
		actions.remove(action);
	}
	
	public int getActionsCount(){
		return actions.size();
	}
	
	public String getActionsContents(){
		StringBuilder list = new StringBuilder("");
		for(Action act: actions){
			list.append(String.format(" = %s = \n %s \n [ %s ] \n\n", 
					act.getTitle(), act.getDate().toString(), act.getDescription()));
		}
		return list.toString();
	}
	
	public String getInfoText(){
		StringBuilder info = new StringBuilder("User info:\n");
		info.append(String.format("Name: %s %s\n", name, surname));
		info.append(String.format("Age: %d\n", age));
		info.append(String.format("Rating: %f\n", rating));
		info.append(String.format("Adress: %s\n", adress));
		return info.toString();
	}
}

/**
 * Класс события для юзера
 * @author Less
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
class Action {
	private Date date;
	private String title;
	private String description;
	
	public Action(){
		this(createDate(2000,1,1), "", "");
	}
	
	public Action(Date date, String title, String description){
		this.date = date;
		this.title = title;
		this.description = description;
	}
	
	public static Date createDate(int y, int m, int d){
		return new Calendar.Builder().setDate(y, m, d).build().getTime();
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}

