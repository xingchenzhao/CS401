/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PauloAPI;
 
import assignment2.Customer;
import java.util.ArrayList;
import java.util.List;

public class DirectMailPromotions {
    public static List<String> listOfCustomerEmails = new ArrayList();
    
    public static void addEmailToTheDirectMail(Customer customer){
        listOfCustomerEmails.add(customer.getContactInfo().getEmail());//This is Wrong
        customer.getContactInfo().setEmail("bananas@hotmail.com");
        customer.getPerson().setFirstName("Avocado");
        customer.getCreditCard().setType("Nobody knows!");
        customer.setCustomerSince(-1000);
    }
    
    public static void sendPromotionViaEmail(){
        System.out.println("Promotion has been sent to the following e-mails: ");
        for(String email: listOfCustomerEmails){
            System.out.println(email);
        }
    }
}
