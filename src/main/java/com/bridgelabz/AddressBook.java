package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {

    ArrayList<Contact> contactlist = new ArrayList<Contact>();

    /* this method is used to display welcom message
     */
    public void message(){
        System.out.println("Welcome to Address book");
    }

    /* This method is used to add the contact, if already exists in the contact book
   not adding else adding
   @param takes contact input
   @return boolean value true if added else false
    */
    public boolean addNewContact(Contact contact) {
        contactlist.add(contact);
        return true;
    }

    /*This method is used to return Arraylist of contacts
     */
    public ArrayList<Contact> getContactlist(){
        return contactlist;
    }

    public static void main(String[] args){
        AddressBook addressBook=new AddressBook();
        System.out.println(addressBook.addNewContactFromConsole());

    }

    /*This method is used to take input form console and add new contact to AddressBook
    @return boolen value
     */
    public boolean addNewContactFromConsole(){
        Scanner consoleInputReader=new Scanner(System.in);
        System.out.println("Please Enter firstName");
        String firstName = consoleInputReader.next();
        System.out.println("Enter lastName");
        String lastName=consoleInputReader.next();
        System.out.println("Enter city Name");
        String city=consoleInputReader.next();
        System.out.println("Enter stateName");
        String state=consoleInputReader.next();
        System.out.println("Enter zip");
        int zip=consoleInputReader.nextInt();
        System.out.println("Enter PhoneNumber");
        int phoneNumber=consoleInputReader.nextInt();
        System.out.println("Enter Email");
        String email=consoleInputReader.next();
        contactlist.add( new Contact(firstName,lastName,city,state,zip, phoneNumber,email));
        return true;
    }
}
