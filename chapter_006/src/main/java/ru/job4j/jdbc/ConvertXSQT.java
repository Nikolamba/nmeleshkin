package ru.job4j.jdbc;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class ConvertXSQT {

    public void convert(File source, File dest, File scheme) {
        String sourceData = this.getFileData(source);
        String schemeData = this.getFileData(scheme);

        try (FileOutputStream fos = new FileOutputStream(dest)) {

            TransformerFactory factory = TransformerFactory.newInstance();

            Transformer transformer = factory.newTransformer(
                    new StreamSource(new ByteArrayInputStream(schemeData.getBytes())));

            transformer.transform(new StreamSource(new ByteArrayInputStream(sourceData.getBytes())),
                    new StreamResult(fos));

        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileData(File file) {
        String fileData = null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            fileData = sb.toString();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return fileData;
    }
}
