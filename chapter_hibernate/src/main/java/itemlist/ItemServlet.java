package itemlist;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class ItemServlet extends HttpServlet {

    private final Logic logic = Logic.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> items = logic.loadItems(req);
        if (items != null) {
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter printWriter = resp.getWriter();
            printWriter.append(mapper.writeValueAsString(items));
            printWriter.flush();
        } else {
            resp.sendRedirect(String.format("%s/error.html", req.getContextPath()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (logic.create(req)) {
            resp.sendRedirect(String.format("%s/index.html", req.getContextPath()));
        } else {
            resp.sendRedirect(String.format("%s/error.html", req.getContextPath()));
        }
    }
}
