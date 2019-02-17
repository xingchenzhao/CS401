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

public class ContactInfo {
    private String email;
    private String phoneNumber;
    private String homeAddress;

    public ContactInfo(String email, String phoneNumber, String homeAddress) {
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setHomeAddress(homeAddress);
    }

    public ContactInfo(ContactInfo contactInfo) {
        this.email = contactInfo.getEmail();
        this.phoneNumber = contactInfo.getPhoneNumber();
        this.homeAddress = contactInfo.homeAddress;
    }

    public static ContactInfo createNewContactInfo(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Email:  ");
        String tempEmail = keyboard.nextLine();
        System.out.print("Phone Number:   ");
        String tempPhoneNumber =keyboard.nextLine();
        System.out.print("Home Address:   ");
        String tempHomeAddress = keyboard.nextLine();

        return new ContactInfo(tempEmail,tempPhoneNumber,tempHomeAddress);
    }

    @Override
    public String toString() {
        StringBuilder sbContactInfo = new StringBuilder();
        sbContactInfo.append("Email: ").append(email).append("\n");
        sbContactInfo.append("Phone Number: ").append(phoneNumber).append("\n");
        sbContactInfo.append("Home Address: ").append(homeAddress).append("\n");
        return sbContactInfo.toString();
    }

    public void setEmail(String email) {
        String emailFormat1 = "@";
        String emailFormat2 = ".com";
        if (email.toLowerCase().contains(emailFormat1.toLowerCase())
                && email.toLowerCase().contains(emailFormat2.toLowerCase())) {
            this.email = email;
        } else {
            System.out.println("This is an invalid Email Address.");
        }
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;

    }

    public void setHomeAddress(String homeAddress) {
        if (homeAddress instanceof String) {
            this.homeAddress = homeAddress;
        } else {
            System.out.print("This is an invalid Home Address.");
        }
    }


    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    //Check if the object is an integer
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
