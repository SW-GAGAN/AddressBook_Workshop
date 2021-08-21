package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AddressBookTeste {
    AddressBook addressBook;

    @Test
    public void whenGivenAddressbook_whenAdded_shouldReturnContactList() {
        addressBook = new AddressBook();
        addressBook.message();
        Contact contact = new Contact("Gagan", "Reddy", "Bengaluru", "Karnataka", 560099
                , 872228961, "gaganreddy@gmail.com");
        addressBook.addNewContact(contact);
        ArrayList<Contact> contactList = addressBook.getContactlist();
        Assertions.assertEquals(1, contactList.size());
    }

    @Test
    public void givenContactsDataFromConsole_WhenAdded_shouldReturnTrue() {
        addressBook = new AddressBook();
        Assertions.assertTrue(addressBook.addNewContactFromConsole());
    }
}