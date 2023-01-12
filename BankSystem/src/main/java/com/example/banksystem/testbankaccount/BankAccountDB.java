package com.example.banksystem.testbankaccount;

public class BankAccountDB {
    private final BankDB db = new BankDB();
    public Account account = new Account();

    //Inizializza ai valori immessi
    public BankAccountDB(String nome, String cognome, String bank_number, double balance) {
        db.addAccount(nome, cognome, bank_number, 0);
        account.setFirstname(nome);
        account.setLastname(cognome);
        account.setBank_number(bank_number);
        account.setBalance(balance);
    }

    public BankAccountDB(String nome, String cognome, String bank_number) {
        db.addAccount(nome, cognome, bank_number, 0);
        account.setFirstname(nome);
        account.setLastname(cognome);
        account.setBank_number(bank_number);
        account.setBalance(0);
    }

    public BankAccountDB(String bank_number) {
        String[] read = db.findAccount(bank_number);
        if (read != null) {
            account.setFirstname(read[0]);
            account.setLastname(read[1]);
            account.setBank_number(bank_number);
            account.setBalance(Double.parseDouble(read[2]));
        } else throw new IllegalArgumentException("Questo account non è stato trovato!");
    }



    public void deposit(double amount) {
        account.setBalance(account.getBalance() + amount);
        db.updateBalance(account.getBank_number(), account.getBalance());
    }

    public void withdraw(double amount) {
        account.setBalance(account.getBalance() - amount);
        db.updateBalance(account.getBank_number(), account.getBalance());
    }

    public double getBalance() {
        return account.getBalance();
    }


    public String getAccountNumber() { return account.getBank_number(); }
}
