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

public class Person {
    private String firstName;
    private String lastName;
    private int ssn;
    private int age;

    public Person(String firstName, String lastName, int ssn, int age) {
        setFirstName(firstName);
        setLastName(lastName);
        setSsn(ssn);
        setAge(age);

    }

    public Person(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.ssn = person.ssn;
        this.age = person.age;
    }

    public static Person createNewPerson(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("First Name:  ");
        String tempFirstName = keyboard.nextLine();
        System.out.print("Last Name:   ");
        String tempLastName =keyboard.nextLine();
        System.out.print("SSN:   ");
        int tempSsn = keyboard.nextInt();
        System.out.print("Age:  ");
        int tempAge = keyboard.nextInt();

        return new Person(tempFirstName,tempLastName,tempSsn,tempAge);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customor: ").append(firstName).append(" ").append(lastName).append("\n");
        sb.append("Social Security Number: ").append(ssn).append("\n");
        sb.append("Age: ").append(age).append("\n");
        return sb.toString();
    }

    public void setFirstName(String firstName) {
        if (firstName instanceof String) {
            this.firstName = firstName;
        } else {
            System.out.println("This is an invalid First Name.");
        }
    }

    public void setLastName(String lastName) {
        if (lastName instanceof String) {
            this.lastName = lastName;
        } else {
            System.out.println("This is an invalid Last Name.");
        }
    }

    public void setSsn(int ssn) {

            this.ssn = ssn;

    }

    public void setAge(int age) {
        if (age >= 0 && age <= 150 && isInteger(age)) {
            this.age = age;
        } else {
            System.out.println("This is an invalid Age.");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSsn() {
        return this.ssn;
    }

    public int getAge() {
        return age;
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
