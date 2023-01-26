package com.example.banksystem.servlet;

import com.example.banksystem.Actions;
import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import com.example.banksystem.model.Movement;
import com.example.banksystem.observer.CardObserver;
import com.example.banksystem.observer.MovementObserver;
import com.example.banksystem.operation.HolderOperation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import static com.example.banksystem.model.Encryption.*;

/**
 * Servlet per l'aggiunta degli account nel database, automaticamente genera anche la carta per ciascun utente creato
 */
@WebServlet(name = "adminAddAccount", value = "/admin-addaccount")
public class AdminAddAccountServlet extends HttpServlet {
    String url;
    Connection con;

    /**
     * @return Restituisce un array di stringhe che contiene la carta
     * Il primo elemento [0] sarà il numero della carta, il secondo elemento [1] sarà il CVV
     */
    public static String[] generateCard() {
        String[] results = new String[2];

        //Genera automaticamente il numero di carta e il CVV
        String numbers = "0123456789";
        String FRandom = numbers;
        SecureRandom sr = new SecureRandom();
        StringBuilder card_number = new StringBuilder(12);
        int cvv;
        Random r = new Random();

        for (int i = 0; i < 12; i++) {
            int randomInt = sr.nextInt(FRandom.length());
            char randomChar = FRandom.charAt(randomInt);
            card_number.append(randomChar);
        }

        results[0] = String.valueOf(card_number);
        results[1] = String.valueOf(r.nextInt(999) + 100);

        return results;
    }

    /**
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        request.getRequestDispatcher("admin-addaccount.jsp").forward(request, response);
    }

    /**
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String cf = request.getParameter("cf");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String account_type = request.getParameter("accounttype");
        String residence = request.getParameter("address");
        String username =  request.getParameter("username");
        String card_type = request.getParameter("cardtype");

        String password = cf;
        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key ;
        try {
            key = createSecretKey(password.toCharArray(), salt, iterationCount, keyLength);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        
        String originalPassword = password;

        String encryptedPassword = null;
        try {
            encryptedPassword = encrypt(originalPassword, key);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }

        String decryptedPassword = null;
        try {
            decryptedPassword = decrypt(encryptedPassword, key);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }


        Holder h = new Holder(username, firstname, lastname, cf, null, account_type, residence, 0, encryptedPassword);
       // holderOperation.add(h);
        Actions.getInstance().holderOperation.add(h);
        k.put(key,encryptedPassword);

        // System.out.println(keyy + "   " + k.get(keyy));
        addP(key, encryptedPassword);


        // System.out.println("k to string = " + k.toString());
        //add((SecretKeySpec) k,encryptedPassword);

        String[] randomCard = generateCard();
        LocalDate carddeadline = LocalDate.now().plusYears(10);
        CardObserver.getInstance().add(new Card(card_type + " of " + firstname, randomCard[0], cf, Integer.valueOf(randomCard[1]),card_type, carddeadline, 0));

        switch (account_type) {
            case "Premium" :
                MovementObserver.getInstance().add(new Movement("welcomepremium", LocalDate.now(), randomCard[0]));
                break;
            case "Enterprise":
                MovementObserver.getInstance().add(new Movement("welcomeenterprise", LocalDate.now(), randomCard[0]));
                break;
        }

        request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
    }

}