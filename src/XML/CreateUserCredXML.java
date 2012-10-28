package XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
    
	ArrayList<String> myData;
	ArrayList<String> myData2;
	ArrayList<Integer> myData3;
	ArrayList<Integer> myData4;
	
    Document dom;
    private String fileLocation;
    private int count;

   
    public CreateUserCredXML(ArrayList<String> xUserName, ArrayList<String> xPassword, ArrayList<Integer> xID, ArrayList<Integer> type, String iFileLocation) {
        this.count = 1;
        this.fileLocation = iFileLocation;
        //create a list to hold the data
        myData = new ArrayList<String>();
        myData2 = new ArrayList<String>();
        myData3 = new ArrayList<Integer>();
        myData4 = new ArrayList<Integer>();
        	
       
        
        for (int i = 0; i < xUserName.size(); i++) {
			myData.add(xUserName.get(i));
			myData2.add(xPassword.get(i));
			myData3.add(xID.get(i));
			myData4.add(type.get(i));
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
        
        
        for (int i = 0; i < myData.size(); i++) {
			Element bookEle = createOrderElement(myData.get(i), myData2.get(i), myData3.get(i), myData4.get(i));
			rootEle.appendChild(bookEle);
            incrementCount();
		}
        
//        Iterator<UserCredentials> it = myData.iterator();
//        while (it.hasNext()) {
//        	UserCredentials b = (UserCredentials) it.next();
//            Element bookEle = createOrderElement(b);
//            rootEle.appendChild(bookEle);
//            incrementCount();
//        }
    }

//This method builds the element representation in xml
    /**
     * This method builds the element representation in the xml file. 
     * @param b
     * @return Element
     */
    public Element createOrderElement(String xU, String xP, int id, int type) {

        Element ele = dom.createElement("Order");
        ele.setAttribute("Subject", String.valueOf(getCount()));
        
        
        Element fnE = dom.createElement("userName");
        org.w3c.dom.Text fnText = dom.createTextNode(xU);
        fnE.appendChild(fnText);
        ele.appendChild(fnE);
        
        Element fnE2 = dom.createElement("password");
        org.w3c.dom.Text fnText2 = dom.createTextNode(xP);
        fnE2.appendChild(fnText2);
        ele.appendChild(fnE2);

        Element fnE3 = dom.createElement("id");
        org.w3c.dom.Text fnText3 = dom.createTextNode(String.valueOf(id));
        fnE3.appendChild(fnText3);
        ele.appendChild(fnE3);
        
        Element fnE4 = dom.createElement("userType");
        org.w3c.dom.Text fnText4 = dom.createTextNode(String.valueOf(type));
        fnE4.appendChild(fnText4);
        ele.appendChild(fnE4);
        

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
