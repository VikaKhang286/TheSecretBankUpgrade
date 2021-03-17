/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import java.io.Serializable;

public class User extends SecretBankAccount implements Serializable{
    private String password;
    public User(String id, String acName, String password) {
        super(id, acName);
        this.password = password;
    }       
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }  
}
