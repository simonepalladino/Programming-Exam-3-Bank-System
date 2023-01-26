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
    /**
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        //Prende in input il nome completo o l'username dell'utente cercato e definisce una lista vuota di correntisti
        String searchname = request.getParameter("searchname");
        List<Holder> holderArrayList = new ArrayList<>();

        //Controlla che l'utente abbia scritto qualcosa di valido nel campo di ricerca
        if (searchname != null && !searchname.replace(" ", "").isEmpty()) {
            //Definisce un iteratore inizializzato sul nome cercato
            Iterator<Holder> iterator = Factory.getIterator(Factory.OperationType.HOLDER, searchname);

            while (iterator.hasNext())
                holderArrayList.add(iterator.next());
        } else {
            holderArrayList = Actions.getInstance().holderOperation.getAll();
        }

        //Imposta l'attributo dei correntisti trovati per visualizzarli nella pagina
        request.setAttribute("accounts", holderArrayList);

        request.getRequestDispatcher("admin-accountchooser.jsp").forward(request, response);
    }
}