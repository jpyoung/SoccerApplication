package XML;
/*
 * Author: Jack Young
 * Date: 09/15/2012
 */
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Models.UserCredentials;

/**
 * @author Jack Young
 *
 */
public class ReadUserCredXML extends Node {
	
	List<UserCredentials> myEmpls;

	/**
	 * @param fileAddress
	 */
	public ReadUserCredXML(String fileAddress) {
		super(fileAddress);
		myEmpls = new ArrayList<UserCredentials>();
	}

	
	public void runExample() {
		parseXmlFile();
		parseDocument();
	}

	
	
	public UserCredentials[] giveMeOrderArray() {
		// Object[] ObjectArray = myEmpls.toArray();
		UserCredentials[] array = (UserCredentials[]) myEmpls.toArray(new UserCredentials[myEmpls.size()]);
		return array;
	}

	/**
	 * This method is used to parse the xml document.  
	 */
	public void parseDocument() {
		Element docEle = dom.getDocumentElement();
		NodeList nl = docEle.getElementsByTagName("Order");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);
				UserCredentials e = getOrder(el);
				myEmpls.add(e);
			}
		}
	}

	
	public UserCredentials getOrder(Element empEl) {
		
//		String fname = getTextValue(empEl, "firstName");
//		String lname = getTextValue(empEl, "lastName");
//		int age = getIntValue(empEl, "age");
//
//		Person p = new Person(fname, lname, age);
		
		return null;
	}

}
