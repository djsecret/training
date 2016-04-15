package com.damon.protobuf.addressbook;

import com.damon.protobuf.addressbook.PersonProtos.Person;
import java.io.*;

/**
 * Created by dongjun.wei on 16/4/6.
 */
public class AddPerson {

    static Person PromptForAddress(BufferedReader stdin, PrintStream stdout) throws IOException {
        //Person对象类似于一个String这种的常量，是immutable的
        //一旦生成，就不可变了
        //所以每个Proto会生成对应message对象的Builder，来构建一个message对象
        Person.Builder personBuilder = Person.newBuilder();
        stdout.print("Enter person ID: ");
        personBuilder.setUid(stdin.readLine());

        stdout.print("Enter name: ");
        personBuilder.setUsername(stdin.readLine());

        stdout.print("Enter gender: ");
        String gender = stdin.readLine();
        if ("boy".equals(gender)) {
            personBuilder.setGender(PersonProtos.Gender.BOY);
        }else if ("girl".equals(gender)) {
            personBuilder.setGender(PersonProtos.Gender.GIRL);
        }

        while (true) {
            stdout.print("Enter a phone number (or leave blank to finish): ");
            String number = stdin.readLine();
            if (number.length() == 0) {
                break;
            }

            Person.PhoneNumber.Builder phoneNumber =
                    Person.PhoneNumber.newBuilder().setNumber(number);

            stdout.print("Is this a mobile, home, or work phone? ");
            String type = stdin.readLine();
            if (type.equals("mobile")) {
                phoneNumber.setType(Person.PhoneType.MOBILE);
            } else if (type.equals("home")) {
                phoneNumber.setType(Person.PhoneType.HOME);
            } else if (type.equals("work")) {
                phoneNumber.setType(Person.PhoneType.WORK);
            } else {
                stdout.println("Unknown phone type.  Using default.");
            }


            personBuilder.addPhone(phoneNumber);
        }

        //最后使用Builder的build方法生成message对象
        return personBuilder.build();
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage:  AddPerson ADDRESS_BOOK_FILE");
            System.exit(-1);
        }

        AddressBookProtos.AddressBook.Builder addressBook = AddressBookProtos.AddressBook.newBuilder();

        try {
            //为了从流中获取Builder，使用的是mergeFrom方法，这个方法对于required和optional会覆盖
            //对于repeated会补充
            addressBook.mergeFrom(new FileInputStream(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        addressBook.addPerson(PromptForAddress(new BufferedReader(new InputStreamReader(System.in)), System.out));

        FileOutputStream fileOutputStream = new FileOutputStream(args[0]);

        addressBook.build().writeTo(fileOutputStream);

        fileOutputStream.close();

    }
}
