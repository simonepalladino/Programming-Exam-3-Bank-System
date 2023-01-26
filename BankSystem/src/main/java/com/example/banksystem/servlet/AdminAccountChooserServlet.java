package com.example.banksystem.servlet;

import com.example.banksystem.Actions;
import com.example.banksystem.Factory;
import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.model.Holder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet per la scelta degli account
 */
@WebServlet(name = "adminAccountChooser", value = "/admin-accountchooser")
public class AdminAccountChooserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        String searchname = request.getParameter("searchname");
        List<Holder> holderArrayList = new ArrayList<>();

        if (searchname != null && !searchname.replace(" ", "").isEmpty()) {
            Iterator<Holder> iterator = Factory.getIterator(Factory.OperationType.HOLDER, searchname);

            while (iterator.hasNext())
                holderArrayList.add(iterator.next());
        } else {
            holderArrayList = Actions.getInstance().holderOperation.getAll();
        }

        request.setAttribute("accounts", holderArrayList);

        request.getRequestDispatcher("admin-accountchooser.jsp").forward(request, response);
    }
}