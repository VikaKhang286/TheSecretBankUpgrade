/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import data.CabinRoom;
import menu.Menu;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int mainChoice = 0, subChoice = 0;
        Menu mainMenu = new Menu("Bank Management System");
        mainMenu.addNewOption("1. Creat new account");
        mainMenu.addNewOption("2. Login");
        mainMenu.addNewOption("3. Quit");
        CabinRoom folder = new CabinRoom();
        folder.loadDataFromFile();
        Menu subMenu = new Menu("");
        subMenu.addNewOption("1. Withdraw");
        subMenu.addNewOption("2. Deposit");
        subMenu.addNewOption("3. Transfer money");
        subMenu.addNewOption("4. Remove account");
        subMenu.addNewOption("5. Logout");
        subMenu.addNewOption("6. Change Password");
        subMenu.addNewOption("7. Show History");
        subMenu.addNewOption("8. Quit");
        menu(mainMenu, subMenu, folder);
    }

    public static void menu(Menu mainMenu, Menu subMenu, CabinRoom folder) {
        int mainChoice = 0, subChoice = 0;        
        do {
            mainMenu.printFirst();
            mainChoice = mainMenu.getChoice();
            switch (mainChoice) {
                case 1:
                    folder.creatNewAccount();
                    mainChoice = 3;
                    break;
                case 2:
                    folder.login();
                    mainChoice = 3;
                    break;
                case 3:
                    System.out.println("Quit program!");
                    return;
            }
        } while (mainChoice != 3);
        do {
            folder.show();
            subMenu.printSecond();
            subChoice = subMenu.getChoice();
            switch (subChoice) {
                case 1:
                    folder.withdraw();
                    break;
                case 2:
                    folder.deposit();
                    break;
                case 3:
                    folder.transferMoney();
                    break;
                case 4:
                    int n = folder.removeAccount();
                    if (n == 1) {
                        menu(mainMenu, subMenu, folder);
                        subChoice = 8;
                    }
                    break;
                case 5:
                    menu(mainMenu, subMenu, folder);
                    subChoice = 8;
                    break;
                case 6:
                    int m = folder.changePassword();
                    if (m == 1) {
                        menu(mainMenu, subMenu, folder);
                        subChoice = 8;
                    }
                    break;
                case 7:
                    folder.showHistory();
                    break;
                case 8:
                    System.out.println("Quit program!");
                    break;
            }
        } while (subChoice != 8);
    }
}