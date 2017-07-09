package cn.javass.xgen.utill.readxml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XmlUtill {
	
	public static Document getDocument(String filePathName) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.parse(XmlUtill.class.getResourceAsStream(filePathName));
		
		doc.normalize();
		
		return doc;
	}
}
