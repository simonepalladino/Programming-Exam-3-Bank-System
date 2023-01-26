package com.example.banksystem.servlet;

import java.io.*;
import java.security.GeneralSecurityException;
import java.sql.*;

import com.example.banksystem.Actions;
import com.example.banksystem.operation.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import static com.example.banksystem.model.Encryption.*;
import javax.crypto.spec.SecretKeySpec;


/**
 * Servlet per il login sia per i correntisti che per gli admin
 */
@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    Connection con;
    String logintype;
    String error;

    public void init() {
        //Codice necessario per il funzionamento delle query su database
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        logintype = request.getParameter("logintype");
        error = request.getParameter("error");

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Boolean done = false;

        //Imposta l'username immesso come attributo di sessione per visualizzarlo nella barra in alto
        HttpSession session = request.getSession();
        session.setAttribute("usertext", request.getParameter("username").toString());
        String username = null, password = null, cf = null;
        String depassword =null;


        //Codice relativo al login del correntista
        //Controlla se l'username e la password sono presenti nella tabella "Holders"
        if (logintype.equals("holder")) {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:banksystem.sqlite");
                Statement stmt = con.createStatement();
                ResultSet rs;

                String key = null;
                SecretKeySpec keytemp ;
                getK();
                username = request.getParameter("username");
                password = request.getParameter("password");
                rs = stmt.executeQuery("SELECT * FROM Holders WHERE username='" + username +"'");
                String decryptedPassword = null;
                if (rs.next()) {
                    depassword = rs.getString("password");
                    cf = rs.getString("cf");
                    for (SecretKeySpec keyy : k.keySet()) {
                        if (k.get(keyy).equals(depassword)){
                            keytemp = keyy;
                            decryptedPassword =  decrypt(depassword,keytemp);
                            System.out.println(decryptedPassword);
                            if (decryptedPassword.equals(password))
                                done = true;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (con != null)
                        con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (done) {
                session.setAttribute("selectedHolder", Actions.getInstance().holderOperation.get(cf));

                if (password.equals(cf))
                    response.sendRedirect("holder-setpassword?cf=" + cf +"&depassword="+ depassword);
                else
                    response.sendRedirect("dashboard?logintype=holder");
            } else {
                response.sendRedirect("login?logintype=holder&error=errore");
            }
        } else {
            //Codice relativo al login admin
            //Controlla se l'username e la password sono presenti nella tabella "Admins"
            try {
                con = DriverManager.getConnection("jdbc:sqlite:banksystem.sqlite");
                Statement stmt = con.createStatement();
                ResultSet rs;

                username = request.getParameter("username");
                password = request.getParameter("password");
                rs = stmt.executeQuery("SELECT * FROM Admins WHERE username='" + username + "' AND password='" + password + "'");

                if (rs.next()) {
                    done = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null)
                        con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (done) {
                response.sendRedirect("dashboard?logintype=admin");
            } else {
                response.sendRedirect("login?logintype=admin&error=errore");
            }
        }
    }
}