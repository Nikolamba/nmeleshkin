package ru.job4j.http;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class DataFilter implements Filter {

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/create") && request.getMethod().equals("POST")) {
            Integer userId = Integer.valueOf(request.getParameter("id"));
            String userLogin = request.getParameter("login");
            if (logic.findById(userId) != null || logic.findByLogin(userLogin) != null) {
                request.getServletContext().setAttribute("error", "User with this id or login already exists");
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/create", request.getContextPath()));
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
