package testing;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import controller.Controller;

/**
 * @author Jack Young
 * @date Oct 29, 2012
 */
public class PDFWriter {
	
	private static String FILE = "UserPdf.pdf";
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	@SuppressWarnings("unused")
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	public static void runPDFwriter(Controller c) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addMetaData(document);
			addTitlePage(document, c);
			addContent(document, c);
			document.close();
			System.out
					.println("--------- PDF Successfully Created:  check out UserPdf.pdf-----------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	  // iText allows to add metadata to the PDF
	private static void addMetaData(Document document) {
		document.addTitle("My first PDF");
		document.addSubject("Using iText");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("Jack Young");
		document.addCreator("Jack Young");
	}

	  private static void addTitlePage(Document document, Controller c)
	      throws DocumentException {
	    Paragraph preface = new Paragraph();
	    addEmptyLine(preface, 1); // We add one empty line
	    preface.add(new Paragraph("Soccer Application - IT 306 : By Group 6", catFont));
	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph("Report generated by: Soccer Application Program, " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	        smallBold));
	    addEmptyLine(preface, 3);
	    preface.add(new Paragraph("This document describes something which is very important ",
	        smallBold));

	    addEmptyLine(preface, 8);

	    document.add(preface);
	    document.newPage();
	  }

	private static void addContent(Document document, Controller c)
			throws DocumentException {

		Anchor anchor = new Anchor("User Information/Data", catFont);
		anchor.setName("UserCredentials Table");
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);
		Paragraph subPara = new Paragraph("Login in Information", subFont);
		Section subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Official      userType = 1"));
		subCatPart.add(new Paragraph("Coach        userType = 2"));
		subCatPart.add(new Paragraph("Player        userType = 3"));
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 3);
		subCatPart.add(paragraph);
		createSecondTable(subCatPart, c);
		subCatPart.add(paragraph);
		addEmptyLine(paragraph, 2);
		createTallyTable(subCatPart, c);
		document.add(catPart);
	}
	
	
	//this method creates the table to display tallys of user table
	public static void createTallyTable(Section subCatPart, Controller c) throws BadElementException {
		PdfPTable table = new PdfPTable(2);
		
		PdfPCell c1 = new PdfPCell(new Phrase(" "));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.CYAN);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Number of..."));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.CYAN);
		table.addCell(c1);
		
		ArrayList<String> xU = c.getUc().getUserNamesArraylist();
		ArrayList<String> xP = c.getUc().getPasswordsArraylist();
		ArrayList<Integer> xID = c.getUc().getIdArraylist();
		ArrayList<Integer> xTYPE = c.getUc().getUserTypeArraylist();
		
		int loopLength = 0;
		if (xU.size() == xP.size()) {
			loopLength = xU.size();
		}
		
		int pCount = 0;
		int duCount = 0;
		int oCount = 0;
		int cCount = 0;
		
		for (int i = 0; i < loopLength; i++) {
			if (xTYPE.get(i) == 1) {
				oCount++;
			} else if (xTYPE.get(i) == 2) {
				cCount++;
			} else if (xTYPE.get(i) == 3) {
				pCount++;
			} else {
				duCount++;
			}
			
		}
	
		table.addCell("Default Users");
		table.addCell(String.valueOf(duCount));
		
		table.addCell("Officials");
		table.addCell(String.valueOf(oCount));
		
		table.addCell("Coaches");
		table.addCell(String.valueOf(cCount));
		
		table.addCell("Players");
		table.addCell(String.valueOf(pCount));
		
		table.addCell("Total");
		table.addCell(String.valueOf(loopLength));
		
		subCatPart.add(table);
	}
	
	//this method creates the table to display username, password, id, userType
	public static void createSecondTable(Section subCatPart, Controller c)
			throws BadElementException {

		PdfPTable table = new PdfPTable(4);

		PdfPCell c1 = new PdfPCell(new Phrase("username"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.CYAN);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("password"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.CYAN);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("id"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.CYAN);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("userType"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.CYAN);
		table.addCell(c1);
		table.setHeaderRows(1);

		ArrayList<String> xU = c.getUc().getUserNamesArraylist();
		ArrayList<String> xP = c.getUc().getPasswordsArraylist();
		ArrayList<Integer> xID = c.getUc().getIdArraylist();
		ArrayList<Integer> xTYPE = c.getUc().getUserTypeArraylist();

		System.out.println("-----------------Printer info---------------");
		System.out.println("username length: " + xU.size());
		System.out.println("PASSWORD length: " + xP.size());
		System.out.println("id length: " + xID.size());
		System.out.println("userType length: " + xTYPE.size());
		System.out.println("--------------------------------------------\n\n");

		int loopLength = 0;
		if (xU.size() == xP.size()) {
			loopLength = xU.size();
		}

		for (int i = 0; i < loopLength; i++) {
			table.addCell(xU.get(i));
			table.addCell(xP.get(i));
			table.addCell(String.valueOf(xID.get(i)));
			table.addCell(String.valueOf(xTYPE.get(i)));
		}

		subCatPart.add(table);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
