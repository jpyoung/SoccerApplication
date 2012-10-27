package XML;


/*
 * Author: Jack Young
 * Date: 09/15/2012
 */
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * The Node class is the parent class for the ReadPersonXML class.
 * Fields include fileAddress, and dom. 
 * @author Jack Young 
 * <p>
 */
public class Node {

	private String fileAddress;
	Document dom;
	

	/**
	 * Argument constructor for the Node class.  It accepts the fileAddress of String type.
	 * @param String fileAddress
	 */
	public Node(String fileAddress) {
		// create a list to hold the employee objects
		this.fileAddress = fileAddress;
	}
	
	/**
	 * Returns the Node dom.
	 * @return Document
	 */
	public Document getDom() {
		return dom;
	}

	/**
	 * Returns the Node fileAddress.
	 * @return String
	 */
	public String getFileAddress() {
		return fileAddress;
	}

	
	/**
	 * The parseXMLFile method is used to parse a given xml file by its file address.  
	 * It uses a try catch block and catches for three exceptions: ParserConfigurationException, SAXException, IOException.
	 */
	public void parseXmlFile() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.parse(getFileAddress());
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	
	/**
	 * The getTextValue method accepts an xml element and the name of the element tag, it then searches
	 * for the tag.  Once the tag is found, set a Nodelist variable equal to the element from which the tag was found.
	 * Then set and return a String variable that is equal to the NodeValue of the FirstChild in the element node.
	 * @param ele
	 * @param tagName
	 * @return String
	 */
	public String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			try {
				textVal = el.getFirstChild().getNodeValue();
			} catch (Exception e) {
				textVal = "NA";
			}
		}
		return textVal;
	}
	
	
	/**
	 * The getIntValue method is used to return a value of type double from the getTextValue method.  
	 * @param ele
	 * @param tagName
	 * @return int
	 */
	public int getIntValue(Element ele, String tagName) {
		return Integer.parseInt(getTextValue(ele, tagName));
	}

	
	/**
	 * The getDoubleValue method is used to return a value of type double from the getTextValue method.  
	 * @param ele
	 * @param tagName
	 * @return double
	 */
	public double getDoubleValue(Element ele, String tagName) {
		return Double.parseDouble(getTextValue(ele, tagName));
	}

}
