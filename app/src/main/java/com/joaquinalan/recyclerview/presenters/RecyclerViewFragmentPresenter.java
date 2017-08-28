package com.joaquinalan.recyclerview.presenters;

import android.content.Context;

import com.joaquinalan.recyclerview.adapters.ContactAdapter;
import com.joaquinalan.recyclerview.data.ContactConstructor;
import com.joaquinalan.recyclerview.fragments.RecyclerViewFragmentViewable;
import com.joaquinalan.recyclerview.pojo.Contact;

import java.util.ArrayList;

/**
 * Created by joaquinalan on 04/02/2017.
 */

public class RecyclerViewFragmentPresenter implements RecyclerViewFragmentPresentable {
    private RecyclerViewFragmentViewable mViewable;
    private Context mContext;
    private ContactConstructor mContactConstructor;
    private ArrayList<Contact> mContacts;

    public RecyclerViewFragmentPresenter(RecyclerViewFragmentViewable recyclerViewFragmentViewable, Context context) {
        mViewable = recyclerViewFragmentViewable;
        mContext = context;
        initiateContacts();
    }

    @Override
    public void initiateContacts() {
        mContactConstructor = new ContactConstructor(mContext);
        mContacts = mContactConstructor.getContacts();
        createRecyclerViewContacts();
    }

    @Override
    public void createRecyclerViewContacts() {
        ContactAdapter adapter = mViewable.getAdapter(mContacts);
        mViewable.initiateRecyclerViewAdapter(adapter);
        mViewable.createVerticalLinearLayout();
    }
}
