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
import PauloAPI.DirectMailPromotions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

public class Assignment2 {
    public static ArrayList<Customer> listOfCustomer = new ArrayList<Customer>();
    public static ArrayList<String> listOfEmail = new ArrayList<String>();
    public static Customer customers = new Customer();

    public static void main(String[] args) {

        Customer customer = new Customer();
        Scanner keyboard = new Scanner(System.in);
        int optionk = 0;
        retrieveFile("Customer.txt");
        do {
            System.out.println("");
            System.out.println("Please choose option: ");
            System.out.println("    1:List Current Customers");
            System.out.println("    2:Add Customer to our Direct Mail");
            System.out.println("    3:Create a new customer");
            System.out.println("    4:Exit");
            int option = keyboard.nextInt();
            if (option == 1) {
                for (Customer c : listOfCustomer) {
                    int counter = listOfCustomer.indexOf(c) + 1;
                    System.out.print("****** Customer # " + counter + "*******");
                    System.out.println(c);
                }
            } else if (option == 2) {
                    System.out.print("Enter customer number to be added to the Direct Mail: ");
                    int numberOfCustomer = keyboard.nextInt();
//                    DirectMailPromotions.addEmailToTheDirectMail(listOfCustomer.get(numberOfCustomer - 1));
                    
                    DirectMailPromotions.listOfCustomerEmails.add(listOfCustomer.get(numberOfCustomer-1).getContactInfo().getEmail());
                    DirectMailPromotions.sendPromotionViaEmail();
                    
            } else if (option == 3) {
                customers = new Customer(customer.createNewCustomer());
                if (equals(customers)) {
                    listOfCustomer.add(customers);
                    writeFile(customers, "Customer.txt");
                    System.out.println("");
                    System.out.println("**********************");
                    System.out.println("The following customer has not been found in our database");
                    System.out.println(customers);
                }
            } else if (option == 4) {
                optionk = 4;
            }
        } while (optionk != 4);
    }


    public static void writeFile(Customer customer1, String FileDirectory) {
        FileWriter fw = null;
        try {
            File f = new File(FileDirectory);
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.append("\n");
        pw.append(customer1.getPerson().getFirstName() + ", " + customer1.getPerson().getLastName() + ", " +
                customer1.getPerson().getSsn() + ", " + customer1.getPerson().getAge() + ", ");
        pw.append(customer1.getContactInfo().getEmail() + ", " + customer1.getContactInfo().getPhoneNumber()
                + ", " + customer1.getContactInfo().getHomeAddress() + ", ");
        pw.append(customer1.getCreditCard().getCreditCardCompany() + ", " + customer1.getCreditCard().getCreditCardNumber()
                + ", " + customer1.getCreditCard().getExpirationYear() + ", " + customer1.getCreditCard().getSecurityCode() + ", " + customer1.getCustomerSince());
        

        
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean equals(Customer customer) {
        boolean check = true;
        for (int k = 0; k < listOfCustomer.size(); k++) {
            if (customer.getPerson().getSsn() == listOfCustomer.get(k).getPerson().getSsn()) {
                System.out.println("");
                System.out.println("***********************");
                System.out.println("Customer has been found in Searz database as shown below: ");
                System.out.println(listOfCustomer.get(k));
                return false;
            }
        }
        return check;
    }


    public static int retrieveFile(java.lang.String FileDirectory) {

        Scanner file = initializeFileScanner(FileDirectory);

        while (file.hasNextLine()) {
            String data = file.nextLine();
            data = data.trim();
            String[] dataSplit = data.split(", ");
            Person person = new Person(dataSplit[0], dataSplit[1], Integer.parseInt(dataSplit[2]), Integer.parseInt(dataSplit[3]));
            ContactInfo contactInfo = new ContactInfo(dataSplit[4], dataSplit[5], dataSplit[6]);
            CreditCard creditCard = new CreditCard(dataSplit[7], Integer.parseInt(dataSplit[8]), Integer.parseInt(dataSplit[9]),
                    Integer.parseInt(dataSplit[10]));
            customers = new Customer(person, contactInfo, creditCard, Integer.parseInt(dataSplit[11]));
            listOfCustomer.add(customers);
        }

        return listOfCustomer.size();
    }


    public static Scanner initializeFileScanner(String FileDirectory) {
        boolean continueLoop = true;
        Scanner FileData = new Scanner("");

        do {
            File File = new File(FileDirectory);
            try {
                FileData = new Scanner(File);
                continueLoop = false;

            } catch (FileNotFoundException e) {
                System.out.println("ERROR: File Not Found. \n"
                        + " Please Input The Directory Of A Valid File:");
                Scanner userInput = new Scanner(System.in);
                FileDirectory = userInput.nextLine().trim();
            }
        } while (continueLoop);
        return FileData;
    }


}
