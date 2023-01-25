package com.example.banksystem.servlet;

import com.example.banksystem.Actions;
import com.example.banksystem.Factory;
import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.model.*;
import com.example.banksystem.observer.MovementObserver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "userCancelOperation", value = "/user-canceloperation")
public class UserCancelOperationServlet extends HttpServlet {
    Holder selectedHolder;
    Movement lastMovement;
    Product lastProduct;
    String backurl;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        selectedHolder = (Holder) session.getAttribute("selectedHolder");
        backurl = request.getParameter("backurl");

        //Ottiene una lista di tutti i movimenti effettuati dall'utente
        lastMovement = null;

        //Itera per selezionare l'ultimo acquisto effettuato (si tratta di un rimborso!)
        Iterator<Movement> recentMovIterator = Factory.getMovementUserIterator(selectedHolder.getCf(), true);
        while (recentMovIterator.hasNext()) {
            Movement rec = recentMovIterator.next();
            String type = Actions.getInstance().productOperation.get(rec.getProduct_id()).getType();

            if (!type.equals("deposit") && !type.equals("withdraw") && !type.equals("upgrade")) {
                lastMovement = Actions.getInstance().movementOperation.get(rec.getId_mov());
                break;
            }
            /* Si ferma immediatamente se trova un rimborso, per non ricorrere a eventuali
                    rimborsi duplicati */
            if (rec.getProduct_id().equals("refund"))
                break;
        }

        if (lastMovement == null) {
            //Non è stato trovato alcun valido movimento annullabile
            response.sendRedirect("user-errorpage.jsp?error=nooperation&backurl=" + backurl);
            return;
        }

        lastProduct = Actions.getInstance().productOperation.get(lastMovement.getProduct_id());
        String productName = lastProduct.getProduct_name();
        String quote = lastProduct.getQuote();
        String type = lastProduct.getType();

        MovementInfo movInfo = new MovementInfo(lastMovement.getMov_date(), lastMovement.getCard_number_FK(), lastMovement.getPrice(), productName, quote, type);

        request.setAttribute("movement", movInfo);

        request.getRequestDispatcher("user-canceloperation.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String todo = request.getParameter("todo");
        if (todo == null) todo = "";

        if (todo.equals("yes")) {
            //Se l'operazione è annullabile, allora...
            if (goRefund(selectedHolder, lastProduct)) {
                MovementObserver.getInstance().add(new Movement("refund", LocalDate.now(), lastMovement.getCard_number_FK(), -lastMovement.getPrice()));
            } else {
                //Altrimenti...
                response.sendRedirect("user-errorpage.jsp?error=canceloperation&backurl=" + backurl);
                return;
            }
        }

        response.sendRedirect(backurl);
    }

    /**
     * Controlla se il correntista può effettuare il rimborso secondo alcune regole specifiche del sistema.
     * Una persona con contratto Basic può effettuare il rimborso solo sulle categorie "food" e "coffee"
     * Una persona con contratto Premium può effettuare il rimborso anche sulle categorie "accessories", "headphones" e "phone".
     * Una persona con contratto Enterprise può effettuare il rimborso anche sulle categorie "tv" e "pc".
     * @param holder correntista dal quale si vuole leggere la tipologia di contratto
     * @param product prodotto sul quale si vuole leggere la tipologia
     * @return restuisce vero se le caratteristiche vengono rispettate altrimenti falso
     */
    public static boolean goRefund(Holder holder, Product product) {
        if (product.getType().equals("food") || product.getType().equals("coffee"))
            return true;

        if (holder.getContract_type().equals("Premium") || holder.getContract_type().equals("Enterprise")) {
            if (product.getType().equals("accessories") || product.getType().equals("headphones") || product.getType().equals("phone"))
                return true;

            if (holder.getContract_type().equals("Enterprise")) {
                if (product.getType().equals("tv") || product.getType().equals("pc"))
                    return true;
            }
        }

        return false;
    }
}