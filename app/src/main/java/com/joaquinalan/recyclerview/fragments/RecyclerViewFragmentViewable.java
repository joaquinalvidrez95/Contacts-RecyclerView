package com.joaquinalan.recyclerview.fragments;

import com.joaquinalan.recyclerview.adapters.ContactAdapter;
import com.joaquinalan.recyclerview.pojo.Contact;

import java.util.ArrayList;

/**
 * Created by joaquinalan on 04/02/2017.
 */

public interface RecyclerViewFragmentViewable {
    void createVerticalLinearLayout();

    ContactAdapter getAdapter(ArrayList<Contact> contacts);

    void initiateRecyclerViewAdapter(ContactAdapter contactAdapter);
}
