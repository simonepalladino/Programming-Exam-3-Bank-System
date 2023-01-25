package com.example.banksystem.servlet;

import com.example.banksystem.Actions;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "userSetPassword", value = "/user-setpassword")
public class UserSetPasswordServlet extends HttpServlet {
    String cf;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        cf = request.getParameter("cf");

        request.getRequestDispatcher("user-setpassword.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if (!password1.equals(password2)) {
            response.sendRedirect("user-setpassword?passerror=nomatch&cf=" + cf);
        } else {
            if (password1.equals(cf)) {
                response.sendRedirect("user-setpassword?passerror=same&cf=" + cf);
                return;
            }

            Actions.getInstance().holderOperation.updatePassword(Actions.getInstance().holderOperation.get(cf), password1);
            response.sendRedirect("dashboard?logintype=user");
        }
    }
}
