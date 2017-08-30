package com.joaquinalan.recyclerview.model.data;

import com.joaquinalan.recyclerview.R;
import com.joaquinalan.recyclerview.model.pojo.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaquinalan on 05/02/2017.
 */

class ContactConstructor {

    public ContactConstructor() {
    }

    static Iterable<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Joaquín Alvidrez"
                , "6666666"
                , "joaquin_alan@hotmail.com"
                , R.drawable.florence_and_the_machine_logo
        ));

        contacts.add(new Contact("Osama Bin Laden"
                , "83396825"
                , "osama.laden@hotmail.com"
                , R.drawable.led_zeppelin_logo
        ));

        contacts.add(new Contact("Peña Nieto"
                , "111111"
                , "peña_nieto@hotmail.com"
                , R.drawable.florence_and_the_machine_logo
        ));

        contacts.add(new Contact("Juaco"
                , "+521818"
                , "joaquinalvidrez95@gmail.com"
                , R.drawable.led_zeppelin_logo
        ));

        contacts.add(new Contact("Donald Trump"
                , "67678786"
                , "popo@hotmail.com"
                , R.drawable.beatles_logo
        ));

        return contacts;
    }
}
