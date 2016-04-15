package com.damon.protobuf.addressbook;

import com.damon.protobuf.addressbook.PersonProtos.Person;
import java.io.FileInputStream;

/**
 * Created by dongjun.wei on 16/4/7.
 */
public class ListPeople {

    static void Print(AddressBookProtos.AddressBook addressBook) {
        for (Person person : addressBook.getPersonList()) {
            System.out.println("Person ID: " + person.getUid());
            System.out.println("  Name: " + person.getUsername());
            switch (person.getGender()) {
                case BOY:
                    System.out.println("boy");
                    break;
                case GIRL:
                    System.out.println("girl");
                    break;
            }

            for (Person.PhoneNumber phoneNumber : person.getPhoneList()) {
                switch (phoneNumber.getType()) {
                    case MOBILE:
                        System.out.print("  Mobile phone #: ");
                        break;
                    case HOME:
                        System.out.print("  Home phone #: ");
                        break;
                    case WORK:
                        System.out.print("  Work phone #: ");
                        break;
                }
                System.out.println(phoneNumber.getNumber());
            }
        }
    }

    public static void main(String[] args) throws Exception{
        if (args.length != 1) {
            System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE");
            System.exit(-1);
        }

        //获取一个Proto（message）对象，不是Builder，使用Proto对象的parseFrom方法，得到一个不可变的只读对象
        AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook.parseFrom(new FileInputStream(args[0]));

        Print(addressBook);
    }
}
