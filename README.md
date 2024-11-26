# Banking System Project

This project implements a **Banking System** for managing **bank accounts** and related operations. The system offers two types of users:

- **Administrator (Admin)**
- **Account Holder (Holder)**

## Features

### Administrator Features
- **Add Account Holder**: Add a new account holder by searching by name, surname, or account number.
- **Remove Account Holder**: Remove an account holder by searching by name, surname, or account number.

### Account Holder Features
- **View Transactions**: Access all transaction history (by card or total for all cards).
- **Make Purchases**: Perform purchases using a linked debit or credit card.
- **Cancel Last Transaction**: Request to cancel the last transaction, and the refunded amount will be credited back to the account balance.
- **Access Services**: Available services based on account type (**Basic**, **Premium**, or **Enterprise**).
- **Deposit/Withdraw**: Deposit or withdraw funds and choose which card to deposit into.

### General System Rules
- **Basic Contract**: 
  - Deposit limit: €5,000/day.
  - Debit card balance cannot go negative.
  - Credit card: Max negative balance of €10.
  - Spending limit: €2,000/day (max 3 transactions).
  
- **Premium Contract**: 
  - Deposit limit: €50,000/day.
  - Debit card balance cannot go negative.
  - Credit card: Max negative balance of €100.
  - Spending limit: €10,000/day (max 10 transactions).
  
- **Enterprise Contract**:
  - Deposit limit: €1,000,000/day.
  - Debit card balance cannot go negative.
  - Credit card: Max negative balance of €1,000.
  - Spending limit: €100,000/day (unlimited transactions).

## Technologies Used
- **Web Application**: Developed with **Apache Tomcat 10.1.5** and **Java Servlets**.
- **Database**: An external database is used to store account and transaction data consistently.

This project leverages **design patterns** and **exception handling** to maintain the integrity of all banking operations and ensure compliance with the defined rules. 

---

### Requirements
- **Java 8+**
- **Apache Tomcat 10.1.5**
- **JDBC-compliant database**

## Installation
1. Clone the repository.
2. Set up the database (details provided in the database section).
3. Deploy the project on **Apache Tomcat**.
4. Start the server and access the application via your browser.

---

Feel free to modify the **installation steps** based on your project's setup instructions!
