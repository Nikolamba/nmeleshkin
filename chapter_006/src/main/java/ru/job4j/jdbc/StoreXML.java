package ru.job4j.jdbc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StoreXML {

    private File target;

    StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Entry> list) {
        try {
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = context.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(new Entries(list), target);
        } catch (JAXBException jaxcExc) {
            jaxcExc.printStackTrace();
        }
    }

    @XmlRootElement
    public static class Entries {
        private List<Entry> values;

        Entries() { }

        Entries(List<Entry> values) {
            this.values = values;
        }

        public List<Entry> getValues() {
            return this.values;
        }

        public void setValues(List<Entry> values) {
            this.values = values;
        }
    }

    @XmlRootElement
    public static class Entry {
        private int value;

        Entry() { }

        Entry(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
