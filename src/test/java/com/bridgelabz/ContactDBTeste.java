package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ContactDBTeste {
    AddressBook addressBook = new AddressBook();
    Contact contact1=new Contact("Gagan" ,"Sr","Bengaluru" , "Karnataka" , 560099 ,2121232, "gagansr@gmail.com");
    Contact contact2=new Contact("Srinivar","KV","Bengaluru" , "Karnataka" , 560076 ,2121232, "srinivar@gmail.com");
    Contact contact3=new Contact("inivar","KV","Mysuru"    , "Karnataka" , 560076 ,2121232, "nivar@gmail.com");
    Contact contact4=new Contact("iar","KV", "Vizak"    , "Andhra"    , 780076 ,2121232, "iar@gmail.com");

    @Test
    public void givenQueryToFetchData_whenMatctedWithExistingList_shouldReturnTrue() throws SQLException {
        addressBook.addMultipleContacts(new Contact[]{contact1, contact2, contact3, contact4});
        AddressBook addressBook2 = new AddressBook();
        List<Contact> contactList = addressBook2.getDataFromDB();
        Assertions.assertEquals(contactList, addressBook.contactlist);
    }

    @Test
    public void givenQueryToFetchData_whenMatctedWithNONExistingList_shouldReturnTrue() throws SQLException {
        addressBook.addMultipleContacts(new Contact[]{contact1, contact2, contact3});
        AddressBook addressBook2 = new AddressBook();
        List<Contact> contactList = addressBook2.getDataFromDB();
        Assertions.assertNotEquals(contactList, addressBook.contactlist);
    }

    @BeforeEach
    public void loaddata() {
        addressBook.addMultipleContacts(new Contact[]{contact1, contact2, contact3});
    }

    @Test
    public void givenContactUpdateDetails_whenUpdated_shouldReturnTrue() throws SQLException {
        boolean check = addressBook.updatePhoneNumberDB("Gagan", "Sr", 87876890);
        Assertions.assertTrue(check);
    }

    @Test
    public void givenContactUpdateDetails_whenSyncWithDatabase_shouldReturnTrue() throws SQLException {
        addressBook.updatePhoneNumberDB("Gagan", "Reddy", 872228961);
        Assertions.assertEquals(87872121,addressBook.contactlist.get(0).phoneNumber);
    }

    @Test
    public void givenContactUpdateDetails_whenUpdatedNonExistingContact_shouldReturnFalse() throws SQLException {
        boolean check = addressBook.updatePhoneNumberDB("arun", "KV", 87876890);
        Assertions.assertTrue(!check);
    }
    @Test
    public void givenDateToFetchContacts_whenExecutedQuery_shouldReturnContacts() throws SQLException {
        LocalDate startDate=LocalDate.of(2021,04,19);
        LocalDate endDate= LocalDate.of(2021,04,30);
        List<Contact> contactListbyDate=addressBook.getContactDBbyDate(startDate,endDate);
        Assertions.assertEquals(contact1,contactListbyDate.get(0));
    }
    @Test
    public void givenCityName_whenSearchedByQuery_shouldReturnContactsInCity() throws SQLException {
        List<Contact> contactsListCityDB = addressBook.getContactsBySCityDB("Bengaluru");
        List<Contact> contactListCity=addressBook.getContactsByCity("Bengaluru");
        Assertions.assertEquals(contactListCity,contactsListCityDB);
    }

    @Test
    public void givenNonExistingCityName_whenSearchedByQuery_shouldReturnZeroContactsList() throws SQLException {
        List<Contact> contactsListCityDB = addressBook.getContactsBySCityDB("Belagavi");
        List<Contact> contactListCity=addressBook.getContactsByCity("Belagavi");
        Assertions.assertEquals(contactListCity,contactsListCityDB);
    }

    @Test
    public void givenContactData_whenInsertedIntoMultipleTable_shouldReturnContactList(Contact contact5, Contact contact6) throws SQLException {
        AddressBook addressBook2=new AddressBook();
        addressBook2.addMultipleContacts(new Contact[]{contact5,contact6});
        boolean insertion=addressBook2.insertIntoTable();
    }

}