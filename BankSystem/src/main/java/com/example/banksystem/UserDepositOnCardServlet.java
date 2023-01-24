package com.example.banksystem;

import com.example.banksystem.model.Holder;
import com.example.banksystem.model.Movement;
import com.example.banksystem.observer.CardObserver;
import com.example.banksystem.observer.MovementObserver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.banksystem.exception.*;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "userDepositOnCard", value = "/user-depositoncard")
public class UserDepositOnCardServlet extends HttpServlet {
    Holder selectedHolder;
    String selectedCard;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        selectedHolder = (Holder) session.getAttribute("selectedHolder");
        selectedCard = request.getParameter("card");

        request.setAttribute("card", selectedCard);

        request.getRequestDispatcher("user-depositoncard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String moneyString = "";
        double moneyDouble = 0;
        boolean cancel = false;
        try {
            moneyString = request.getParameter("money");
            moneyDouble = Double.parseDouble(moneyString);
        } catch (Exception e) {
            cancel = true;
        }

        if (!cancel) {
            if (moneyDouble > 0) {
                Movement deposit = new Movement("deposit", LocalDate.now(), selectedCard, moneyDouble);

                try {
                    //Controlla se l'utente può permettersi di effettuare il deposito
                    DepositExceedException.checkDepositLimit(selectedHolder, deposit);

                    MovementObserver.getInstance().add(deposit);
                } catch (DepositExceedException depositExceedException) {
                    System.out.println(depositExceedException.toString());
                    response.sendRedirect("user-errorpage.jsp?error=nodeposit&backurl=user-deposit");
                    return;
                } catch (Exception e) {
                    response.sendRedirect("user-errorpage.jsp?backurl=user-deposit");
                    return;
                }
            } else {
                response.sendRedirect("user-depositoncard?error=input&card=" + selectedCard);
                return;
            }
        }

        response.sendRedirect("user-deposit");
    }
}