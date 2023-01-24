package com.example.banksystem;

import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import com.example.banksystem.model.Movement;
import com.example.banksystem.observer.MovementObserver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "userPlan", value = "/user-plan")
public class UserPlanServlet extends HttpServlet {
    Holder selectedHolder;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        selectedHolder = (Holder) session.getAttribute("selectedHolder");

        request.getRequestDispatcher("user-plan.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String selected = request.getParameter("selected");

        String prodID = null;
        double price = 0;

        //Decisioni riguardo i costi di cambio programma
        switch (selected) {
            case "Premium":
                if (selectedHolder.getContract_type().equals("Basic")) {
                    prodID = "basic-to-premium";
                    price = 100;
                }
                break;
            case "Enterprise":
                if (selectedHolder.getContract_type().equals("Basic")) {
                    prodID = "basic-to-enterprise";
                    price = 1000;
                } else if (selectedHolder.getContract_type().equals("Premium")) {
                    prodID = "premium-to-enterprise";
                    price = 900;
                }
                break;
        }

        if (price > 0) {
            //Scegli la prima carta con fondi disponibili
            Card selectedCard = null;
            for (Object cardObject : selectedHolder.getCards()) {
                selectedCard = (Card) cardObject;
                if (selectedCard.getBalance() >= price)
                    break;
            }

            if (selectedCard == null) {
                response.sendRedirect("user-errorpage.jsp?error=nofund&backurl=dashboard?logintype=user");
                return;
            }

            MovementObserver.getInstance().add(new Movement(prodID, LocalDate.now(), selectedCard.getCard_number(), -price));
        }

        Actions.getInstance().holderOperation.updatePlan(selectedHolder, selected);

        response.sendRedirect("dashboard?logintype=user");
    }
}