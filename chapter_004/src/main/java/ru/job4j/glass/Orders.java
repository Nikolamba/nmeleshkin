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
    private Map<String, Book> books = new HashMap<>();

    public Book getGlass(String book) {
        return books.get(book);
    }

    private void addOrder(Order order) {
        if (!books.containsKey(order.getBook())) {
            books.put(order.getBook(), new Book(order.getBook()));
        }
        books.get(order.getBook()).add(order);
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
                Order order = new Order(Integer.valueOf(attributes.getValue(4)));
                order.setType(qName);
                order.setBook(attributes.getValue(0));
                order.setAction(attributes.getValue(1));
                order.setPrice(Double.valueOf(attributes.getValue(2)));
                order.setVolume(Integer.valueOf(attributes.getValue(3)));
                addOrder(order);
            } else if (qName.equals("DeleteOrder")) {
                Order order = new Order(Integer.valueOf(attributes.getValue(1)));
                order.setType(qName);
                order.setBook(attributes.getValue(0));
                addOrder(order);
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
