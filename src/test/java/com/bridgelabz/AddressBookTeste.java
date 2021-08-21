package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookTeste {
    AddressBook addressBook;
    Contact contact= new Contact("Gagan","Sr","Bengaluru","Karnataka",560099
            ,966339366,"gagansr@gmail.com");
    Contact contact1=new Contact("Srini","Rr","Bengaluru","Karnataka",560076
            ,526157122,"srinivas@gmail.com");
    Contact newContact =new Contact("Gagan","Rs","indore","MP",
            21212,6876786,"gaganRs@gmail.com");
    Contact newContact2 =new Contact("Manuew","re","indore","MP",
            21212,6876786,"manushetty7799@gmail.com");

    @Test
    public void givenAddressbook_whenAdded_shouldReturnContactList(){
        addressBook=new AddressBook();
        addressBook.message();
        Contact contact= new Contact("Gagan","Sr","Bengaluru","Karnataka",560099
                ,966339366,"gagansr@gmail.com");
        Contact contact1=new Contact("Srini","Rr","Bengaluru","Karnataka",560076
                ,526157122,"srinivas@gmail.com");
        addressBook.addNewContact(contact);
        addressBook.addNewContact(contact1);
        ArrayList<Contact> contactList= addressBook.getContactlist();
        Assertions.assertEquals(2,contactList.size());
    }


    @Test
    public void givenContactsDataFromConsole_WhenAdded_shouldReturnTrue(){
        addressBook=new AddressBook();
        Assertions.assertTrue(addressBook.addNewContactFromConsole());
    }

    @Test
    public  void givenContactsData_whenAdded_shouldReturnTrue(){
        addressBook=new AddressBook();
        addressBook.addNewContact(contact);
        addressBook.addNewContact(contact1);
        Contact oldContact=addressBook.getContact("Gagan","Sr");
        Contact oldContact2=addressBook.getContact("gurasa","Er");
        Assertions.assertEquals(null,oldContact2);
        Assertions.assertTrue(addressBook.updateContact(oldContact,newContact));
        Assertions.assertEquals(newContact,addressBook.getContact(newContact));
        Assertions.assertEquals(false,addressBook.updateContact(oldContact2,newContact2));
    }

}