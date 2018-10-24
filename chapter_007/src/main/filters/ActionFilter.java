import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class ActionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (request.getRequestURI().contains("/newuser")) {
            if (role.equals("User")) {
                request.getServletContext().setAttribute("error", "Access denied");
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/", request.getContextPath()));
                return;
            }
        }
        if (request.getRequestURI().contains("/newuser") && request.getMethod().equals("POST")) {
            int autorization = Integer.valueOf(request.getParameter("autorization"));
            if (role.equals("Mandator") && autorization == 1) {
                request.getServletContext().setAttribute("error", "Cannot create user with specified rights");
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/", request.getContextPath()));
            } else {
                filterChain.doFilter(req, resp);
            }
        } else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
