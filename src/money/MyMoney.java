/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package money;

import java.util.Scanner;

public class MyMoney {
    private static Scanner sc = new Scanner(System.in);
    public static boolean checkName(String name){        
	for (int i = 0; i < name.length(); i++)
		if (name.charAt(i) >= 33 && name.charAt(i) <= 64 || name.charAt(i) >= 91 && name.charAt(i) <= 96 
                || name.charAt(i) >= 123 && name.charAt(i) <= 127 || name.charAt(i) >= 0 && name.charAt(i) <= 31)
                    return false;		
	return true;
    } 
     
    public static String getName(String inputMsg, String outputErrorMsg) {
        String str;          
        while (true) {
            System.out.print(inputMsg);
            str = sc.nextLine().trim();
            if (str.length() == 0 || str.isEmpty()||checkName(str) == false)
                System.out.println(outputErrorMsg);
            else 
                return str;
        }
    }
     
    public static int getIntegerNum(String inputMsg, String outputErrorMsg){
         int num;
         while(true){
             try {
                 System.out.println(inputMsg);
                 num = Integer.parseInt(sc.nextLine());
                 return num;
             } catch (Exception e) {
                 System.out.println(outputErrorMsg);
             }
         }
     }
     
    public static double getDoubleNum(String inputMsg, String outputErrorMsg) {
        double dnum;        
        while (true) {
            try {
                System.out.print(inputMsg);
                dnum = Double.parseDouble(sc.nextLine());
                return dnum;
            } catch (Exception e) {
                System.out.println(outputErrorMsg);
            }
        }
    }
     
     public static double getDoubleNum(double lowerbound, double upperbound, String inputMsg, String outputErrorMsg) {
        double dn, tmp;      
        if (lowerbound > upperbound) {
            tmp = lowerbound;
            lowerbound = upperbound;
            upperbound = tmp;
        }        
        while (true) {
            try {
                System.out.print(inputMsg);
                dn = Double.parseDouble(sc.nextLine());
                if (dn < lowerbound || dn > upperbound) throw new Exception();                
                return dn;
            } catch (Exception e) {
                System.out.println(outputErrorMsg);
            }
        }
    }
          
     public static int getIntegerNum(int lowerbound, int upperbound, String inputMsg, String outputErrorMsg){
         int in, tmp;
         if(lowerbound > upperbound){
             tmp = lowerbound;
             lowerbound = upperbound;
             upperbound = tmp;
         }
         while(true){
             try {
                 System.out.println(inputMsg);
                 in = Integer.parseInt(sc.nextLine());
                 if(in < lowerbound || in > upperbound)
                     throw new Exception();
                 return in;
             } catch (Exception e) {
                 System.out.println(outputErrorMsg);
             }
         }
     }
     
     public static String getStringByFree(String inputMsg, String outputErrorMsg) {
        String str;          
        while (true) {
            System.out.print(inputMsg);
            str = sc.nextLine().trim();            
            if (str.length() == 0 || str.isEmpty())
                System.out.println(outputErrorMsg);
            else 
                return str;
        }
    }
     
     public static String getStringByFormFirst(String inputMsg, String outputErrorMsg, String format) {
        String stdStr;
        boolean match;        
        while (true) {
            System.out.print(inputMsg);
            stdStr = sc.nextLine();
            match = stdStr.matches(format);
            if (stdStr.length() == 0 || stdStr.isEmpty() || match == false)
                System.out.println(outputErrorMsg);
            else
                return stdStr;            
        }        
    }
     
     public static String getStringByFormSecond(String inputMsg, String outputErrorMsg, String format) {
        String stdStr;
        boolean match;        
        while (true) {
            System.out.print(inputMsg);
            stdStr = sc.nextLine().toUpperCase();
            match = stdStr.matches(format);
            if (stdStr.length() == 0 || stdStr.isEmpty() || match == false)
                System.out.println(outputErrorMsg);
            else
                return stdStr;            
        }
     }
}
