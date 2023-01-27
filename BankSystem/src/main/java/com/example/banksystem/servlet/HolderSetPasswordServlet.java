package com.example.banksystem.servlet;

import com.example.banksystem.Actions;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet utilizzata per impostare la password ai correntisti
 */
@WebServlet(name = "userSetPassword", value = "/holder-setpassword")
public class HolderSetPasswordServlet extends HttpServlet {
    String cf;
    String depassword;

    /**
     * Metodo per il salvataggio delle stringhe inserite in input con il reindirizzamento al file jsp
     * contenente il form per settare una nuova password
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        cf = request.getParameter("cf");
        depassword = request.getParameter("depassword");

        request.getRequestDispatcher("holder-setpassword.jsp").forward(request, response);
    }

    /**
     * In questo metodo dopo la compilazione del form avvengono i vari controlli sulla coerenza dei dati per poi
     * richiamare la funzione di aggiunta della nuova password all'interno del database.
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws IOException
     * @throws ServletException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        //Se le password non corrispondono, allora ricarica la pagina mostrando l'errore di non corrispondenza
        if (!password1.equals(password2)) {
            response.sendRedirect("holder-setpassword?passerror=nomatch&cf=" + cf +"&pass= " +depassword);
        } else {
            //Se le password corrispondono ma sono identiche al CF del correntista, allora rimanda all'errore appropriato
            if (password1.equals(cf)) {
                response.sendRedirect("holder-setpassword?passerror=same&cf=" + cf+"&pass= " +depassword);
                return;
            }

            //Se i controlli vanno a buon fine, allora aggiorna la password
            Actions.getInstance().holderOperation.updatePassword(Actions.getInstance().holderOperation.get(cf), password1,depassword);
            response.sendRedirect("dashboard?logintype=holder");
        }
    }
}

