package XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Models.UserCredentials;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * This CreatePersonXML class is used to write the Person objects data to the PersonData.xml file
 * Fields include myData, dom, fileLocation, count;
 * @author Jack Young
 *
 */
@SuppressWarnings("restriction")
public class CreateUserCredXML {
    
	List<UserCredentials> myData;
    Document dom;
    private String fileLocation;
    private int count;

   
    public CreateUserCredXML(UserCredentials[] iPerson, String iFileLocation) {
        this.count = 1;
        this.fileLocation = iFileLocation;
        //create a list to hold the data
        myData = new ArrayList<UserCredentials>();

        for (int x = 0; x < iPerson.length; x++) {
            myData.add(iPerson[x]);
        }

        System.out.println("CreateXML constructor was called");
        createDocument();
    }

    /**
     * Returns the file location. 
     * @return String
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     * Returns the count. 
     * @return int
     */
    public int getCount() {
        return count;
    }

    /**
     * Used to increment the count variable.
     */
    public void incrementCount() {
        this.count++;
    }

    /**
     * This is a controller method that is calls: createDOMTree() and printToFile() methods.
     */
    public void runExample() {
        createDOMTree();
        printToFile();
        System.out.println("Generated file successfully.");
    }

    /**
     * This method is used to create the physical xml document. 
     */
    public void createDocument() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();
        } catch (ParserConfigurationException pce) {
            //dump the info
            System.out.println("Error while trying to instantiate DocumentBuilder " + pce);
            System.exit(1);
        }
    }

// This method creates the XML structure
    /**
     * This method is used to create the dom tree for the xml data structure.  
     */
    public void createDOMTree() {
        Element rootEle = dom.createElement("Tracker"); //create the root element <Books>
        dom.appendChild(rootEle);
        Iterator<UserCredentials> it = myData.iterator();
        while (it.hasNext()) {
        	UserCredentials b = (UserCredentials) it.next();
            Element bookEle = createOrderElement(b);
            rootEle.appendChild(bookEle);
            incrementCount();
        }
    }

//This method builds the element representation in xml
    /**
     * This method builds the element representation in the xml file. 
     * @param b
     * @return Element
     */
    public Element createOrderElement(UserCredentials b) {

        Element ele = dom.createElement("Order");
        ele.setAttribute("Subject", String.valueOf(getCount()));
        
        
//        Element fnE = dom.createElement("firstName");
//        Text fnText = dom.createTextNode(b.getFirstName());
//        fnE.appendChild(fnText);
//        ele.appendChild(fnE);
//
//        Element lnE = dom.createElement("lastName");
//        Text lnText = dom.createTextNode(b.getLastName());
//        lnE.appendChild(lnText);
//        ele.appendChild(lnE);
//
//        Element aE = dom.createElement("age");
//        Text aText = dom.createTextNode(String.valueOf(b.getAge()));
//        aE.appendChild(aText);
//        ele.appendChild(aE);

        return ele;
    }


    /**
     * This method is used to print the xml dom tree to the Person.xml file. Uses a try catch block, 
     * the catch block is looking for an IOException.    
     */
    @SuppressWarnings("restriction")
	public void printToFile() {

        try {
          
			OutputFormat format = new OutputFormat(dom);
            format.setIndenting(true);
           
			XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(new File(getFileLocation())), format);
            serializer.serialize(dom);
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}
