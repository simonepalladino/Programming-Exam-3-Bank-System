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

public class  Encryption {

    public static Map<SecretKeySpec,String> k = new HashMap<SecretKeySpec,String>();
    public static String url = "jdbc:sqlite:banksystem.sqlite";
    public static Connection con;

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
           // System.out.println("chiave string = " + temp);
          // System.out.println("chiave chave = " + originalKey);
        }

            for (SecretKeySpec keyyy : k.keySet()) {
               // System.out.println(keyyy + "   " + k.get(keyyy));
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

    public static void addP(SecretKeySpec k , String valore){
        try {
            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO cripted values (?, ?)");
            String encodedKey = Base64.getEncoder().encodeToString(k.getEncoded());

            stmt.setString(1, encodedKey);
            stmt.setString(2, valore);
            stmt.execute();


            //connessione al database
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




    public static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }

    public static String encrypt(String dataToEncrypt, SecretKeySpec key) throws GeneralSecurityException, UnsupportedEncodingException {
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters parameters = pbeCipher.getParameters();
        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
        byte[] cryptoText = pbeCipher.doFinal(dataToEncrypt.getBytes("UTF-8"));
        byte[] iv = ivParameterSpec.getIV();
        return base64Encode(iv) + ":" + base64Encode(cryptoText);
    }

    private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException {
        String iv = string.split(":")[0];
        String property = string.split(":")[1];
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    }

    private static byte[] base64Decode(String property) throws IOException {
        return Base64.getDecoder().decode(property);
    }
}
