/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.util.ArrayList;
import money.MyMoney;

public class Menu {
    private String title;
    private ArrayList<String> listOption = new ArrayList();

    public Menu(String title) {
        this.title = title;
    }
    
    public void addNewOption(String option){
        listOption.add(option);
    }
    
    public void printFirst(){
        if(listOption.isEmpty()){
            System.out.println("No item in the menu");
            return;
        }
        System.out.print("\n------------------------------------\n");
        System.out.println(title);
        for (String x : listOption) {
            System.out.println(x);
        }
    }
    public void printSecond(){
        if(listOption.isEmpty()){
            System.out.println("No item in the menu");
            return;
        }
        for (String x : listOption) {
            System.out.println(x);
        }
    }
    
    public int getChoice(){
        int maxOption = listOption.size();
        String inputMsg = "Choose [1 to " + maxOption + "]: ";
        String outputErrorMsg = "Please choose option 1 to " + maxOption;
        return MyMoney.getIntegerNum(1, maxOption, inputMsg, outputErrorMsg);
    }
    
}
