package com.bridgelabz;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.json.simple.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactsIOoperation {

    /*This method used to write the data of Contacts to a file
     * param it takes contactlist, and filename
     * return boolean value
     */
    public boolean writeToFile(ArrayList<Contact> contactlist, File filename) throws IOException, FileNotFoundException {
        FileWriter fileWriter = new FileWriter(filename);
        try {
            fileWriter.append("firstName,lastName,PhoneNumber,city,state,zip,EmailId"+"\n");
            for (Contact contact: contactlist) {
                fileWriter.append(contact.firstName+",");
                fileWriter.append(contact.lastName+",");
                fileWriter.append(contact.phoneNumber+",");
                fileWriter.append(contact.city+",");
                fileWriter.append(contact.state+",");
                fileWriter.append(String.valueOf(contact.zip)+",");
                fileWriter.append(contact.email+",");
                fileWriter.append("\n");
            }
        } catch (FileNotFoundException e){
            System.out.println("Please enter proper file");
        } catch (IOException e) {
            return false;
        }
        fileWriter.flush();
        fileWriter.close();
        return true;
    }

    /*This method used to read contactsList data from a File
     * @param File to read csv file
     * @return List of Contact
     */
    public  List<Contact> readFromCsv(File fileName) throws IOException,ArrayIndexOutOfBoundsException {
        AddressBook addressBook=new AddressBook();
        try (Reader reader = Files.newBufferedReader(Paths.get(String.valueOf(fileName)));
             CSVReader csvReader = new CSVReader(reader);) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                Contact contact=new Contact(record[0], record[1],record[3],record[4],
                        Integer.parseInt(record[5]),Integer.parseInt(record[2]),record[6]);
                addressBook.addNewContact(contact);
            }
        }
        catch (CsvValidationException e) {  e.printStackTrace();}
        catch (CsvException e) { e.printStackTrace();}
        catch (ArrayIndexOutOfBoundsException e){ e.printStackTrace();}
        return addressBook.contactlist;
    }

    /* This method is used write JSonFile,
    @param takes filename
    @return bollean value
    */
    public boolean writeToFileJson(List<Contact> contactList,File fileName) throws IOException {
        for (int i = 0; i < contactList.size(); i++) {
            Contact contact = contactList.get(i);
            JSONObject jsObject=new JSONObject();
            JSONObject obj=new JSONObject();
            jsObject.put("id",i);
            jsObject.put("firstname",contact.firstName);
            jsObject.put("lastname",contact.lastName);
            jsObject.put("phoneNumber",contact.phoneNumber);
            jsObject.put("city",contact.city);
            jsObject.put("state",contact.state);
            jsObject.put("zip",contact.zip);
            jsObject.put("email",contact.email);
            BufferedWriter fileWriter=new BufferedWriter(new FileWriter(fileName, true));
            try {
                char doubleQuotes='"';
                if(i==0)
                    fileWriter.append("{ "+ doubleQuotes+"contact"+doubleQuotes+":[");
                fileWriter.append(jsObject.toJSONString());
                if(i<contactList.size()-1)
                    fileWriter.append(",\n");
                if(i==contactList.size()-1)
                    fileWriter.append("]}");
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                fileWriter.flush();
                fileWriter.close();
            }
        }
        return true;
    }

}