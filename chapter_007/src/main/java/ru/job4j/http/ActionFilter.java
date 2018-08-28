package ru.job4j.http;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Nikolay Melehskin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class ActionFilter implements Filter {

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/signin")) {
            filterChain.doFilter(req, resp);
            return;
        }
        HttpSession session = request.getSession();
        Role currentRole = logic.findByLogin((String) session.getAttribute("login")).getRole();
        User currentUser = logic.findByLogin((String) session.getAttribute("login"));

        if (request.getRequestURI().contains("/edit") && request.getMethod().equals("GET")) {
            if (currentRole.getName().equals("administrator")) {
                filterChain.doFilter(req, resp);
            } else if (Integer.valueOf(request.getParameter("id")) != currentUser.getId()) {
                req.getServletContext().setAttribute("error", "Error changing of user");
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/", request.getContextPath()));
            } else {
                filterChain.doFilter(req, resp);
            }
        } else if (request.getRequestURI().contains("/create")) {
            if (currentRole.getName().equals("administrator")) {
                filterChain.doFilter(req, resp);
            } else {
                req.getServletContext().setAttribute("error", "Error creating of user");
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/", request.getContextPath()));
            }
        } else if (request.getRequestURI().contains("/edit") && request.getMethod().equals("POST")) {
            if (currentRole.getName().equals("administrator")) {
                filterChain.doFilter(req, resp);
            } else if (currentRole.getName().equals("user")) {
                if (!request.getParameter("autorization").equals(currentUser.getRole().getName())) {
                    req.getServletContext().setAttribute("error", "Error changing of role");
                    ((HttpServletResponse) resp).sendRedirect(String.format("%s/", request.getContextPath()));
                }
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
