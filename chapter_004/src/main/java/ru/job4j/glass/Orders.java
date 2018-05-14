package ru.job4j.glass;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * Класс загружает данные из указанного файла и формирует список заявок
 * для каждого эмитента
 */
public class Orders {
    private Map<String, Glass> glasses = new HashMap<>();

    public Glass getGlass(String book) {
        return glasses.get(book);
    }

    private void addRequest(Request request) {
        if (!glasses.containsKey(request.getBook())) {
            glasses.put(request.getBook(), new Glass(request.getBook()));
        }
        glasses.get(request.getBook()).add(request);
    }

    public void parseFile(String fileName) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(false);
        SAXParser parser;

        InputStream xmlData;
        try {
            xmlData = new FileInputStream(fileName);
            parser = factory.newSAXParser();
            parser.parse(xmlData, new MyParser());
        } catch (IOException | ParserConfigurationException | SAXException exc) {
            exc.printStackTrace();
        }
    }

    class MyParser extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            if (qName.equals("AddOrder")) {
                Request request = new Request(Integer.valueOf(attributes.getValue(4)));
                request.setType(qName);
                request.setBook(attributes.getValue(0));
                request.setAction(attributes.getValue(1));
                request.setPrice(Double.valueOf(attributes.getValue(2)));
                request.setVolume(Integer.valueOf(attributes.getValue(3)));
                addRequest(request);
            } else if (qName.equals("DeleteOrder")) {
                Request request = new Request(Integer.valueOf(attributes.getValue(1)));
                request.setType(qName);
                request.setBook(attributes.getValue(0));
                addRequest(request);
            }
            super.startElement(uri, localName, qName, attributes);
        }

        @Override
        public void startDocument() throws SAXException {
            System.out.println("Начало разбора документа!");
            super.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            System.out.println("Разбор документа окончен!");
        }
    }
}
