package com.example.banksystem.model;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Classe utilizzata per criptare/decriptare le password degli utenti
 * all'interno del database.
 * Viene utilizata una struttura dati Hashmap per la conservazione
 * delle coppie chiave valore per criptare le password dell'utente.
 */
public class Encryption {

    public static Map<SecretKeySpec,String> k = new HashMap<SecretKeySpec,String>();
    public static String url = "jdbc:sqlite:banksystem.sqlite";
    public static Connection con;

    /**
     * Metodo per l'inizializzazione dell' hashmap k
     * utilizzando la tabella cripted in cui sono
     * salvate le coppie chiave valore
     */
    public static void getK() {
        try {
        con = DriverManager.getConnection(url);
        Statement stmt = con.createStatement();
        ResultSet rs;
        String depassword =null;
        String temp;
        SecretKeySpec keyy ;

        rs = stmt.executeQuery("SELECT * FROM cripted ");

        while (rs.next()) {
           temp = rs.getString("key");
           depassword =rs.getString("pass");
            byte[] decodedKey = Base64.getDecoder().decode(temp);
            SecretKeySpec originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            k.put(originalKey,depassword);
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

    }

    /**
     * Metodo per l'eliminazione di una coppia chiave valore nella tabella
     * cripted
     */
    public static void deleteP(String pass) {
        try {

            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("DELETE FROM cripted WHERE pass=(?)");

            stmt.setString(1, pass);
            stmt.execute();

            k.remove(pass);
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
    }

    /**
     * Metodo per l'inserimento di una coppia chiave valore
     * all'interno della tabella cripted
     * @param k chiave
     * @param valore
     *
     */
    public static void addP(SecretKeySpec k , String valore){
        try {
            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO cripted values (?, ?)");
            String encodedKey = Base64.getEncoder().encodeToString(k.getEncoded());

            stmt.setString(1, encodedKey);
            stmt.setString(2, valore);
            stmt.execute();
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
    }


    /**
     * Metodo per creazione di una chiave associata ad un password passata in input.
     * @param password : password associata alla chiave.
     * @param salt :  è un dato casuale che utilizziamo come input aggiuntivo che esegue l’hashing dei dati
     * @param iterationCount : numero di iterazioni dell' algoritmo. Usato per prevenire attacchi di bruteforce
     * @param keyLength : lunghezza della chiave
     * @return una nuova chiave
     * @throws NoSuchAlgorithmException generata se una modifica è nulla, vuota oppure in un formato non valido
     * @throws InvalidKeySpecException se la chiave fornita è inappropriata
     */
    public static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }

    /**
     * Metodo per criptare la password passata in input con una determinata chiave.
     * @param dataToEncrypt : stringa da criptare
     * @param key : chiave con cui deve essere criptata la stringa
     * @return la stringa passata in input come stringa criptata
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */
    public static String encrypt(String dataToEncrypt, SecretKeySpec key) throws GeneralSecurityException, UnsupportedEncodingException {
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  //attraversa la lista dei provider di sicurezza registrati
        pbeCipher.init(Cipher.ENCRYPT_MODE, key); //inizializzazione per l'encrypt_mode
        AlgorithmParameters parameters = pbeCipher.getParameters();
        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
        byte[] cryptoText = pbeCipher.doFinal(dataToEncrypt.getBytes("UTF-8"));
        byte[] iv = ivParameterSpec.getIV();
        return base64Encode(iv) + ":" + base64Encode(cryptoText); // conversione dell'array di byte in stringa
    }

    /**
     * Metodo di conversione
     * @param bytes : array di byte da convertire in stinga
     * @return stringa convertita
     */
    private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Metodo per decriptare una password passata in input con la chiave ad essa associata
     * @param string: password da decriptare
     * @param key: chiave associata alla password
     * @return la password decriptata
     */
    public static String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException {
        String iv = string.split(":")[0];
        String property = string.split(":")[1];
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    }

    /**
     * Metodo per la conversione da striga a secretkey
     * @param property : stringa da convertire
     * @return stringa convertita
     */
    private static byte[] base64Decode(String property) throws IOException {
        return Base64.getDecoder().decode(property);
    }
}
