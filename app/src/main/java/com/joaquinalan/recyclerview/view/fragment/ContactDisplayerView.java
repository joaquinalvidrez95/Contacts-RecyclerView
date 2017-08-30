package com.joaquinalan.recyclerview.view.fragment;

import com.joaquinalan.recyclerview.model.pojo.Contact;

/**
 * Created by joaquinalan on 04/02/2017.
 */

public interface ContactDisplayerView {
    void createVerticalLinearLayout();

    void initiateRecyclerViewAdapter(Iterable<Contact> contacts);

    void startContactDetailActivicty(Contact contact);

    void showContactClicked(Contact contact);

    void showContactLiked(Contact contact);

    void setContacts(Iterable<Contact> contacts);

    void onNumberOfLikesChanged(Contact contact);
}
