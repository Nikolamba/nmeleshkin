package ru.job4j.jdbc;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class ParserXML {

    private int totalCount;

    ParserXML(File dest) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            Handler handler = new Handler();
            parser.parse(dest, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    class Handler extends DefaultHandler {

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            System.out.println("Start parse XML...");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (qName.equals("entry")) {
                totalCount += Integer.valueOf(attributes.getValue("field"));
            }
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            System.out.println("End parse XML.");
        }
    }
}


