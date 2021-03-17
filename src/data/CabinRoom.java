/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import money.MyMoney;

public class CabinRoom {
    private String id = "";
    private String accountName = "";
    private String password = "";
    private double money = 0;
    private int pos = -1;
    private ArrayList<SecretBankAccount> bank = new ArrayList<>();
    private ArrayList<SecretBankAccount> user = new ArrayList<>();
    private ArrayList<SecretBankSystem> secBankSys = new ArrayList<>();
    private String contentPro;

    public void creatNewAccount() {
        int check = 0;
        String confirmPassword;
        do {
            id = MyMoney.getStringByFormFirst("Input id account: ", "Id must contain at least 5 characters",
                    "^[a-z0-9._-]{5,15}$");
            pos = bank.indexOf(new SecretBankAccount(id));
            if (pos >= 0) {
                System.out.println("Id already exists");
            }
        } while (pos != -1);
        accountName = MyMoney.getName("Input Name: ", "Invalid name! ");
        do {
            check = 0;
            password = MyMoney.getStringByFormFirst("Input password: ",
                    "Password must contain at least 6 characters, including uppercase letters, lower case letters, numeric characters and 1 special character",
                    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,}$");
            confirmPassword = MyMoney.getStringByFree("Input confirm password: ", "Must not be left blank");
            if (!password.equals(confirmPassword)) {
                System.out.println("Incorrect password");
                check = -1;
            }
        } while (check != 0);
        money = MyMoney.getDoubleNum(0.1, 9999999, "Input money: ", "Invalid money");
        password = encode(password);
        bank.add(new SecretBankAccount(id, accountName, money));
        user.add(new SecretBankAccount(id, password));
        storeDataFromFile(bank, "bank.dat");
        storeDataFromFile(user, "user.dat");
    }
  
    public String encode(String src) {
        String result = "";
        for (int i = 0; i < src.length(); i++) {
            char c = (char) (((int) src.charAt(i) - 123) % 256);
            result += c;
        }
        return result;
    }

    public void login() {
        int check = 0;
        do {
            check = 0;
            id = MyMoney.getStringByFormFirst("Input id account: ", "Id must contain at least 5 characters",
                    "^[a-z0-9._-]{5,15}$");
            password = MyMoney.getStringByFormFirst("Input password: ",
                    "Password must contain at least 6 characters, including uppercase letters, lower case letters, numeric characters and 1 special character",
                    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,}$");
            password = encode(password);
            password = PasswordField.readPassword("Enter password: ");
            System.out.println("Password entered was: " + password);
            pos = bank.indexOf(new SecretBankAccount(id));
            if (pos == -1) {
                System.out.println("Account does not axist");
                check = -1;
            } else if (!user.get(pos).getPassword().equals(password)) {
                System.out.println("Incorect password!");
                check = -1;
            }
        } while (check != 0);
        contentPro = "login accout";
        secBankSys.add(new SecretBankSystem(id, contentPro));
        storeDataFromFile(secBankSys, "log.dat");
    }
    
    public int changePassword() {
        int check = 0;
        String newPassword;
        password = MyMoney.getStringByFormFirst("Input password: ",
                "Password must contain at least 6 characters, including uppercase letters, lower case letters, numeric characters and 1 special character",
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,}$");
        password = encode(password);
        if (!user.get(pos).getPassword().equals(password)) {
            System.out.println("Incorect password!");
            return -1;
        }
        do {
            check = 0;
            password = MyMoney.getStringByFormFirst("Input new password: ",
                    "Password must contain at least 6 characters, including uppercase letters, lower case letters, numeric characters and 1 special character",
                    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,}$");
            newPassword = MyMoney.getStringByFree("Input new password again: ", "Must not be left blank");
            if (!password.equals(newPassword)) {
                System.out.println("Incorrect password");
                check = -1;
            }
        } while (check != 0);
        contentPro = "change password";
        password = encode(password);
        user.get(pos).setPassword(password);
        storeDataFromFile(user, "user.dat");
        secBankSys.add(new SecretBankSystem(id, contentPro));
        storeDataFromFile(secBankSys, "log.dat");
        return 1;

    }
    public void withdraw() {
        double m = 0;
        m = bank.get(pos).getMoney();
        money = MyMoney.getDoubleNum(0.1, m, "Enter the amount you want to withdraw: ", "Invalid money");
        bank.get(pos).setMoney(m - money);
        storeDataFromFile(bank, "bank.dat");
        storeDataFromFile(user, "user.dat");
        System.out.println("withdraw successful");
        contentPro = "You have withdrawn " + money;
        secBankSys.add(new SecretBankSystem(id, contentPro));
        storeDataFromFile(secBankSys, "log.dat");
    }

    public void deposit() {
        double m = 0;
        String c = null;
        m = bank.get(pos).getMoney();
        money = MyMoney.getDoubleNum(0.1, 9999999, "Enter the amount you want to deposit: ", "Invalid money");
        c = MyMoney.getStringByFormSecond("Enter Y to continue deposit, enter N to exit! ",
                "you should only enter 1 character(Y or N)", "^[N|Y]$");
        if (c.charAt(0) != 78) {
            bank.get(pos).setMoney(m + money);
            storeDataFromFile(bank, "bank.dat");
            storeDataFromFile(user, "user.dat");
        } else {
            System.out.println("Deposit failed");
        }
    }

    public void show(){
        pos = bank.indexOf(new SecretBankAccount(id));
        System.out.printf("|%-15s||%-15s|%-15s|\n", "ID", "Name", "Balance");
        bank.get(pos).show();
    }
     public void showHistory(){
        for (SecretBankSystem basys : secBankSys) {
            if(basys.getId().equalsIgnoreCase(id))
                basys.show();
        }
    }
    public void transferMoney() {
        String id2 = "";
        int pos2 = 0;
        double m = 0;
        String c;
        id2 = MyMoney.getStringByFormFirst("Input id account: ", "Id must contain at least 5 characters",
                "^[a-z0-9._-]{5,15}$");
        pos2 = bank.indexOf(new SecretBankAccount(id2));
        if (pos2 == -1) {
            System.out.println("Account does not exist");
        }
        if (pos2 == pos) {
            System.out.println("This is your account");
        } else {
            System.out.println("name of the account you want to transfer: "
                    + bank.get(pos2).getAccountName());
            m = bank.get(pos).getMoney();
            money = MyMoney.getDoubleNum(0.1, m, "Enter the amount you want to withdraw: ", "Invalid money");
            c = MyMoney.getStringByFormSecond("Enter Y to continue transfer, enter N to exit! ",
                    "you should only enter 1 character(Y or N)", "^[N|Y]$");
            if (c.charAt(0) != 78) {
                bank.get(pos).setMoney(m - money);
                m = bank.get(pos2).getMoney();
                bank.get(pos2).setMoney(money + m);
                storeDataFromFile(bank, "bank.dat");
                storeDataFromFile(user, "user.dat");
                System.out.println("Transfer successful! ");
            } else {
                System.out.println("Transfer cancel!");
            }
        }
    }

    public int removeAccount() {
        String c = MyMoney.getStringByFormSecond("Enter Y to confirm, Enter N to cancel! ",
                "you should only enter 1 character(Y or N)", "^[N|Y]$");
        if (c.charAt(0) != 78) {
            bank.remove(pos);
            user.remove(pos);
            for (int i = 0; i < secBankSys.size(); i++) {
                if(secBankSys.get(i).getId().equalsIgnoreCase(id))
                    secBankSys.remove(i);
            }
            storeDataFromFile(secBankSys, "log.dat");
            storeDataFromFile(bank, "bank.dat");
            storeDataFromFile(user, "user.dat");
            System.out.println("Remove successful!");
            return 1;
        } else {
            System.out.println("Remove cancel");
        }
        return -1;
    }

    public void storeDataFromFile(ArrayList bank, String file) {
        try {
            FileOutputStream fo = new FileOutputStream(file);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(bank);
            fo.close();
            oo.close();
        } catch (IOException e) {
            System.err.println("file storage error!" + e);
        }
    }

    public void loadDataFromFile() {
        try {
            FileInputStream fi = new FileInputStream("bank.dat");
            ObjectInputStream oi = new ObjectInputStream(fi);
            bank = (ArrayList) oi.readObject();
            fi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("File reading error" + e);
        }
        try {
            FileInputStream fi = new FileInputStream("user.dat");
            ObjectInputStream oi = new ObjectInputStream(fi);
            user = (ArrayList) oi.readObject();
            fi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("File reading error" + e);
        }
        try {
            FileInputStream fi = new FileInputStream("log.dat");
            ObjectInputStream oi = new ObjectInputStream(fi);
            secBankSys = (ArrayList) oi.readObject();
            fi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("File reading error" + e);
        }
    }
}
