package com.joaquinalan.recyclerview.model.data;

import com.joaquinalan.recyclerview.model.pojo.Contact;

/**
 * Created by joaquinalan on 28/08/2017.
 */

public interface ContactsPersistence {
    void likeContact(Contact contact);

    Iterable<Contact> getContacts();

    int getContactLikes(Contact contact);

    Contact getContact(int contactId);
}
