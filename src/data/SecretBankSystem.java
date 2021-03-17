/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.Date;

public class SecretBankSystem implements Serializable{
    Date datetime;
    String id, contentPro;

    public SecretBankSystem(String id, String contentPro) {
        this.id = id;
        this.datetime = new Date();
        this.contentPro = contentPro;
    }
    public Date getDateTime() {
        return datetime;
    }
    public void setDateTime(Date datetime) {
        this.datetime = datetime;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getContentPro() {
        return contentPro;
    }
    public void setContentPro(String contentPro) {
        this.contentPro = contentPro;
    }   
    public void show(){
        System.out.println("|" + datetime + "|" + id + "|" + contentPro);
    }   
}
