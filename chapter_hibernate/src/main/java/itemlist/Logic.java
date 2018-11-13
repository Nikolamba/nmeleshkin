package itemlist;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Logic {

    private final DAOItem itemsStore = DAOItem.getINSTANCE();
    private final static Logic INSTANCE = new Logic();

    private Logic() { }

    public static Logic getInstance() {
        return INSTANCE;
    }

    public boolean create(HttpServletRequest req) {
        boolean result = false;
        if (checkStringParam("desc", req)) {
            Item item = new Item();
            item.setDesc(req.getParameter("desc"));
            item.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            item.setDone(false);
            itemsStore.create(item);
            result = true;
        }
        return result;
    }

    public List<Item> loadItems(HttpServletRequest req) {
        List<Item> items = null;
        if (checkStringParam("check", req)) {
            String check = req.getParameter("check");
            if (check.equals("all")) {
                items = itemsStore.findAll();
                Collections.sort(items);
            } else if (check.equals("done")) {
                items = itemsStore.findNotDone();
                Collections.sort(items);
            }
        }
        return items;
    }

    private boolean checkStringParam(String param, HttpServletRequest req) {
        return req.getParameter(param) != null;
    }
}
