package ru.job4j.upload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;

@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/upload_view/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String rootPath = System.getProperty("catalina.home");
        File fileDir = new File(rootPath + File.separator + "tmpfiles");
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        System.out.println("File name is: " + fileName);
        InputStream fileContent = filePart.getInputStream();

        File file = new File(fileDir + File.separator + fileName);
        file.createNewFile();

        if (!file.exists()) {
            throw new ServletException("File doesn't exists on server.");
        }
        System.out.println("File location on server::" + fileDir.getAbsolutePath());

        FileOutputStream fos = new FileOutputStream(fileDir + File.separator + fileName);
        int data = fileContent.read();
        while (data != -1) {
            System.out.print((char) data);
            fos.write(data);
            data = fileContent.read();
        }
        fos.close();
        fileContent.close();
    }
}
