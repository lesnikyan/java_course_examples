/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serialize;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 *
 * @author Less
 */

@XmlRegistry
public class ObjectFactory {
	private static final QName qName = new QName(XMLConstants.NULL_NS_URI, "data");
	
	@XmlElementDecl(name="CalendarRoot")
	public JAXBElement<UserCalendar> createData(UserCalendar value){
		return new JAXBElement<UserCalendar>(qName, UserCalendar.class, null, value);
	}
}
