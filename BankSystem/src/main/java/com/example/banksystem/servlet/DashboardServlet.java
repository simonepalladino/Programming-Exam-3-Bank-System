package com.example.banksystem.servlet;

import com.example.banksystem.Actions;
import com.example.banksystem.Factory;
import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.model.*;
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
 * Servlet che visualizza i movimenti delle carte dei correntisti e la dashboard di ciascun correntista
 */
@WebServlet(name = "dashboard", value = "/dashboard")
public class DashboardServlet extends HttpServlet {
    String logintype;
    Holder selectedHolder;

    /**
     * Metodo per il controllo del logintype con relativo inirizzamento della dashboard coerente.
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        logintype = request.getParameter("logintype");

        if (logintype.equals("admin"))
            //Carica pagina relativa all'admin
            request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
        else {
            //Carica pagina relativa all'utente
            selectedHolder = (Holder) session.getAttribute("selectedHolder");

            //Imposta la lista di carte dell'utente
            List<Card> cardArrayList = selectedHolder.getCards();
            double totalBalance = 0;

            for (Card card : cardArrayList) {
                totalBalance += card.getBalance();
            }
            request.setAttribute("cardList", cardArrayList);
            request.setAttribute("balance", totalBalance);

            //Imposta la lista di transazioni recenti dell'utente (massimo tre)
            Iterator<Movement> recentMovIterator = Factory.getMovementUserIterator(selectedHolder.getCf(), true);
            List<MovementInfo> recentMovements = new ArrayList<>();

            //Inizializza la lista di movimenti recenti richiamando il costruttore di MovementInfo
            int i = 1;
            while (recentMovIterator.hasNext() && i <= 3) {
                Movement rec = recentMovIterator.next();
                Product temp = Actions.getInstance().productOperation.get(rec.getProduct_id());
                String productName = temp.getProduct_name();
                String quote = temp.getQuote();
                String type = temp.getType();

                recentMovements.add(new MovementInfo(rec.getMov_date(), rec.getCard_number_FK(), rec.getPrice(), productName, quote, type));
                i++;
            }
            request.setAttribute("recentMovements", recentMovements);

            request.getRequestDispatcher("holder-dashboard.jsp").forward(request, response);
        }
    }
}
