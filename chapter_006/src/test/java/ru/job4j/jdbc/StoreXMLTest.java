package ru.job4j.jdbc;

import org.junit.Test;
import java.io.File;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StoreXMLTest {

    @Test
    public void whenUseStoreXMLShouldResultToFile() {

        String url = "jdbc:sqlite:store.db";
        File source = new File("target.xml");
        File scheme = new File("scheme.xml");
        File dest = new File("dest.xml");

        Properties config = new Properties();
        config.setProperty("url", url);

        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.generate(10);

        StoreXML storeXML = new StoreXML(source);
        storeXML.save(storeSQL.getEntries());

        new ConvertXSQT().convert(source, dest, scheme);

        assertThat(new ParserXML(dest).getTotalCount(), is(55));
    }

}