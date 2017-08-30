package com.joaquinalan.recyclerview.model;

import com.joaquinalan.recyclerview.model.pojo.Contact;

/**
 * Created by joaquinalan on 28/08/2017.
 */

public interface ContactsDisplayerPresentable {
    void likeContact(Contact contact, ContactsDisplayerListener contactsDisplayerListener);


    Iterable<Contact> getContacts();
}
