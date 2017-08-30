package com.joaquinalan.recyclerview.model;

import com.joaquinalan.recyclerview.model.data.ContactsPersistence;
import com.joaquinalan.recyclerview.model.pojo.Contact;

/**
 * Created by joaquinalan on 28/08/2017.
 */

public class ContactsDisplayer implements ContactsDisplayerPresentable {
    private ContactsPersistence mContactsPersistence;

    public ContactsDisplayer(ContactsPersistence contactsDatabaseInterface) {
        mContactsPersistence = contactsDatabaseInterface;
    }

    @Override
    public void likeContact(Contact contact, ContactsDisplayerListener contactsDisplayerListener) {
        mContactsPersistence.likeContact(contact);
        Contact newContact = mContactsPersistence.getContact(contact.getId());
        contactsDisplayerListener.onNumberOfLikesChanged(newContact);
    }

    @Override
    public Iterable<Contact> getContacts() {
        return mContactsPersistence.getContacts();
    }

    public int getContactLikes(Contact contact) {
        return mContactsPersistence.getContactLikes(contact);
    }
}
