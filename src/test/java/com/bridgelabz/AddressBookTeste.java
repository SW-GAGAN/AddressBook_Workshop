package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookTeste {
    AddressBook addressBook;
    Contact contact= new Contact("Gagan","Sr","Bengaluru","Karnataka",560076
            ,966339366,"gagansr@gmail.com");
    Contact contact1=new Contact("Srinivas","Kv","Bengaluru","Karnataka",560076
            ,526157122,"srinivas@gmail.com");
    Contact newContact =new Contact("Gagan","re","indore","MP",
            21212,6876786,"gaganre@gmail.com");
    Contact newContact2 =new Contact("Manuew","re","indore","MP",
            21212,6876786,"manuew@gmail.com");
    Contact contact6= new Contact("Santhosh","Kv","Bengaluru","Karnataka",560076
            ,212139366,"manukvshetty@gmail.com");
    AddressBook addressBookFamily=new AddressBook();
    AddressBook addressBookFriends=new AddressBook();
    MultipleAdressBookContainer multipleAdressBookContainer=new MultipleAdressBookContainer();

    @Test
    public void givenAddressbook_whenAdded_shouldReturnContactList(){
        addressBook=new AddressBook();
        addressBook.message();
        Contact contact= new Contact("Gagan","Sr","Bengaluru","Karnataka",560076
                ,966339366,"manukvshetty@gmail.com");
        Contact contact1=new Contact("Srinivas","Kv","Bengaluru","Karnataka",560076
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
        Contact oldContact=addressBook.getContact("Manu","Kv");
        Contact oldContact2=addressBook.getContact("gurasa","Er");
        Assertions.assertEquals(null,oldContact2);
        Assertions.assertTrue(addressBook.updateContact(oldContact,newContact));
        Assertions.assertEquals(newContact,addressBook.getContact(newContact));
        Assertions.assertEquals(false,addressBook.updateContact(oldContact2,newContact2));
    }

    @Test
    public  void givenContactsData_whenDeletedContact_shouldReturnDeletedContact(){
        addressBook =new AddressBook();
        addressBook.addNewContact(contact);
        addressBook.addNewContact(contact1);
        boolean positiveResult=addressBook.deleteContact("Gagan","Sr");
        Assertions.assertTrue(positiveResult);
        boolean negativeResult2=addressBook.deleteContact("Gagan","Sr");
        Assertions.assertEquals(false,negativeResult2);
    }

    @Test
    public  void givenMultipleContactsData_whenAddedContact_shouldReturnSizeOfAddressBook(){
        addressBook =new AddressBook();
        Contact[] contactsArray={contact,contact1,newContact,newContact2,contact};
        int numberOfContactsAdded=addressBook.addMultipleContacts(contactsArray);
        Assertions.assertEquals(4,numberOfContactsAdded);
    }

    @Test
    public void givenMultipleAddressBooks_whenAdded_shouldReturnNumberOfAddressBooks(){
        Contact[] contactsArray={contact,contact1,newContact,contact};
        Contact[] contactsArray2={contact,contact1,contact6,newContact2};
        AddressBook addressBookHome=new AddressBook();
        addressBookFamily.addMultipleContacts(contactsArray);
        addressBookFriends.addMultipleContacts(contactsArray2);
        multipleAdressBookContainer.addAddressBookList("Family",addressBookFamily);
        multipleAdressBookContainer.addAddressBookList("Friends",addressBookFriends);
        Assertions.assertEquals(2,addressBookFriends.contactlist.size());
        Assertions.assertEquals(addressBookFamily,multipleAdressBookContainer.addressBookDictionary.get("Family"));
    }

}