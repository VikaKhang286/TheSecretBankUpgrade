/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;

public class SecretBankAccount implements Serializable {
    private String id;
    private String accountName, password;   
    private double money;
    public SecretBankAccount() {
    }
    public SecretBankAccount(String id) {
        this.id = id;
    }
    public SecretBankAccount(String id, String password) {
        this.id = id;
        this.password = password;
    }
    public SecretBankAccount(String id, String accountName, double money) {
        this.id = id;
        this.accountName = accountName;
        this.money = money;
    }
    public SecretBankAccount(String id, String accountName, String password, double money) {
        this.id = id;
        this.accountName = accountName;
        this.password = password;
        this.money = money;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String acName) {
        this.accountName = acName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }               
    public void show(){
        System.out.printf("|%-15s|%-15.1f|\n", accountName, money);
    }
    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((SecretBankAccount)obj).getId()); 
    }            
}
