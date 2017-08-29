package com.joaquinalan.recyclerview.model;

import com.joaquinalan.recyclerview.model.data.ContactsDatabaseInterface;
import com.joaquinalan.recyclerview.model.pojo.Contact;

/**
 * Created by joaquinalan on 28/08/2017.
 */

public class ContactsDisplayer implements ContactsDisplayerModel {
    private ContactsDatabaseInterface mContactsDatabaseInterface;

    public ContactsDisplayer(ContactsDatabaseInterface contactsDatabaseInterface) {
        mContactsDatabaseInterface = contactsDatabaseInterface;
    }

    @Override
    public void likeContact(Contact contact) {
        mContactsDatabaseInterface.likeContact(contact);

    }

    @Override
    public Iterable<Contact> getContacts() {
        return mContactsDatabaseInterface.getContacts();
    }

    public int getContactLikes(Contact contact) {
        return mContactsDatabaseInterface.getContactLikes(contact);
    }
}
