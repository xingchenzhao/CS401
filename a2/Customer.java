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
public class Customer {
    private ContactInfo contactInfo;
    private CreditCard creditCard;
    private Person person;
    private int customerSince;

    public Customer(Person person, ContactInfo contactInfo, CreditCard creditCard, int customerSince) {
        this.person = person;
        this.contactInfo = contactInfo;
        this.creditCard = creditCard;
        this.customerSince = customerSince;
    }

    public Customer(Customer customer) {
        person = new Person(customer.person);
        contactInfo = new ContactInfo(customer.contactInfo);
        creditCard = new CreditCard(customer.creditCard);
        customerSince = customer.customerSince;
    }

    public Customer(){

    }
    public Customer createNewCustomer() {
        Person person = Person.createNewPerson();
        ContactInfo contactInfo = ContactInfo.createNewContactInfo();
        CreditCard creditCard = CreditCard.createNewCreditCard();

        int customerSince = 2018;
        Customer newCustomer = new Customer(person, contactInfo, creditCard, customerSince);
        return newCustomer;
    }
    
    public void setCustomerSince(int customerSince){
        this.customerSince = customerSince;
    }

    public Person getPerson(){
        return this.person;
    }

    public ContactInfo getContactInfo() {
        return this.contactInfo;
    }

    public CreditCard getCreditCard() {
        return this.creditCard;
    }

    public int getCustomerSince() {
        return this.customerSince;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(this.person);
        sb.append(this.contactInfo);
        sb.append(this.creditCard);
        sb.append("Customer Since: " + this.customerSince).append("\n");
        
        return sb.toString();
    }

}
