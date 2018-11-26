package carsales.servlets;

import carsales.LogicLayer;
import carsales.dao.*;
import carsales.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddCarServlet extends HttpServlet {

    private final LogicLayer logic = LogicLayer.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("brands", logic.findAllBrands());
        req.setAttribute("types", logic.findAllBodyType());
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("/WEB-INF/car_sales/addcar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        ServletFileUpload upload = new ServletFileUpload(factory);

        Map<String, String> fields = new HashMap<>();
        fields.put("year", null); fields.put("color", null);
        fields.put("models", null); fields.put("body_type", null);
        fields.put("picturePath", null);
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();
                        if (fields.containsKey(fieldName)) {
                            fields.put(fieldName, item.getString());
                        }
                    } else {
                        File fileDir = new File(getServletContext().getRealPath(""));
                        if (!fileDir.exists()) {
                            fileDir.mkdirs();
                        }
                        File filePath = new File(fileDir + File.separator + "car_sales_views" + File.separator + item.getName());
                        System.out.println(filePath);
                        item.write(filePath);
                        fields.put("picturePath", "http://localhost:8080/hibernate/car_sales_views/" + item.getName());
                    }
                }
            }
            if (logic.addCar(req, fields)) {
                req.getRequestDispatcher("/WEB-INF/car_sales/addcar.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(String.format("%s/car_sales_views/cars.html", req.getContextPath()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
