package com.joaquinalan.recyclerview.presenter;

import com.joaquinalan.recyclerview.model.ContactsDisplayerListener;
import com.joaquinalan.recyclerview.model.ContactsDisplayerPresentable;
import com.joaquinalan.recyclerview.model.pojo.Contact;
import com.joaquinalan.recyclerview.view.fragment.ContactDisplayerView;


/**
 * Created by joaquinalan on 04/02/2017.
 */

public class ContactDisplayerFragmentPresenter implements ContactDisplayerFragmentListener, ContactsDisplayerListener {
    private ContactDisplayerView mView;
    private ContactsDisplayerPresentable mModel;

    public ContactDisplayerFragmentPresenter(ContactDisplayerView contactDisplayerView,
                                             ContactsDisplayerPresentable model) {
        mView = contactDisplayerView;
        mModel = model;
        createRecyclerViewContacts();
    }

    private void createRecyclerViewContacts() {
        mView.initiateRecyclerViewAdapter(mModel.getContacts());
        mView.createVerticalLinearLayout();
    }

    @Override
    public void onContactCardViewClicked(Contact contact) {
        mView.showContactClicked(contact);
        mView.startContactDetailActivicty(contact);
    }

    @Override
    public void onThumbUpClicked(Contact contact) {
        mView.showContactLiked(contact);
        mModel.likeContact(contact, this);
//        Iterable<Contact> contacts = mModel.getContacts();
//        mView.setContacts(contacts);
    }

    @Override
    public void onNumberOfLikesChanged(Contact contact) {
        mView.onNumberOfLikesChanged(contact);
    }
}
