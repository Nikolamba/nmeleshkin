package ru.job4j.http;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServletTest {

    @Test
    public void testDeleteUser() throws ServletException, IOException {
        UserCreateServlet userCreateServlet = new UserCreateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("id")).thenReturn("10");
        when(request.getParameter("name")).thenReturn("Misha");
        when(request.getParameter("login")).thenReturn("Misha");
        when(request.getParameter("email")).thenReturn("misha@mail.ru");
        when(request.getParameter("autorization")).thenReturn("user");
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("id", new String[]{"10"});
        parameterMap.put("name", new String[]{"Misha"});
        when(request.getParameterMap()).thenReturn(parameterMap);
        userCreateServlet.doPost(request, response);

        UsersServlet usersServlet = new UsersServlet();
        usersServlet.doPost(request, response);

        assertNull(ValidateService.getInstance().findById(10));
    }

}