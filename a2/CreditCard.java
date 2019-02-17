/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

/**
 *
 * @author zhaoxingchen
 */
import java.util.Scanner;

public class CreditCard {
    private String creditCardCompany;
    private int creditCardNumber;
    private int expirationYear;
    private int securityCode;


    public CreditCard(String creditCardCompany, int creditCardNumber, int expirationYear, int securityCode) {
        setType(creditCardCompany);
        setCreditCardNumber(creditCardNumber);
        setExpirationYear(expirationYear);
        setSecurityCode(securityCode);
    }

    public CreditCard(CreditCard creditCard) {
        this.creditCardCompany = creditCard.getCreditCardCompany();
        this.creditCardNumber = creditCard.getCreditCardNumber();
        this.expirationYear = creditCard.getExpirationYear();
        this.securityCode = creditCard.securityCode;
    }


    public static CreditCard createNewCreditCard() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Credit Card Company:  ");
        String tempCreditCardCompany = keyboard.nextLine();
        System.out.print("Credit Card Number:   ");
        int tempCreditCardNumber = keyboard.nextInt();
        System.out.print("Expiration Year:  ");
        int tempExpirationYear = keyboard.nextInt();
        System.out.print("Security Code:  ");
        int tempSecurityCode = keyboard.nextInt();


        return new CreditCard(tempCreditCardCompany, tempCreditCardNumber, tempExpirationYear, tempSecurityCode);
    }


    public String toString() {
        StringBuilder sbCreditCard = new StringBuilder();
        sbCreditCard.append("Credit Card Company: ").append(creditCardCompany).append("\n");
        sbCreditCard.append("Credit Card Number: ").append(creditCardNumber).append("\n");
        sbCreditCard.append("Expiration Year : ").append(expirationYear).append("\n");
        sbCreditCard.append("Security Code: ").append(securityCode).append("\n");
        return sbCreditCard.toString();
    }

    public void setType(String creditCardCompany) {
        this.creditCardCompany = creditCardCompany;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        if (isInteger(creditCardNumber)) {
            this.creditCardNumber = creditCardNumber;
        } else {
            System.out.println("This is an invalid Credit Card Number.");
        }
    }


    public void setExpirationYear(int expirationYear) {
        if (isInteger(expirationYear)) {
            this.expirationYear = expirationYear;
        } else {
            System.out.println("This is an invalid Expiration Year.");
        }
    }

    public void setSecurityCode(int securityCode) {
        String s = new Integer(securityCode).toString();
        int length = s.length();
        if (length == 3 && isInteger(securityCode)) {
            this.securityCode = securityCode;
        } else {
            System.out.println("This is an invalid Security Code.");
        }
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public String getCreditCardCompany() {
        return creditCardCompany;
    }

    public static boolean isInteger(Object object) {
        if (object instanceof Integer) {
            return true;
        } else {
            String string = object.toString();
            try {
                Integer.parseInt(string);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}