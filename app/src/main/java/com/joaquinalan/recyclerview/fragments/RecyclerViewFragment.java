package com.joaquinalan.recyclerview.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joaquinalan.recyclerview.R;
import com.joaquinalan.recyclerview.adapters.ContactAdapter;
import com.joaquinalan.recyclerview.pojo.Contact;
import com.joaquinalan.recyclerview.presenters.RecyclerViewFragmentPresentable;
import com.joaquinalan.recyclerview.presenters.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by joaquinalan on 29/01/2017.
 */

public class RecyclerViewFragment extends Fragment implements RecyclerViewFragmentViewable {
    private RecyclerView mRecyclerViewContacts;
    private RecyclerViewFragmentPresentable mPresentable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        mRecyclerViewContacts = (RecyclerView) view.findViewById(R.id.recyclerview_recyclerview_contacts);
        mPresentable = new RecyclerViewFragmentPresenter(this, getContext());
        return view;
    }

    @Override
    public void createVerticalLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewContacts.setLayoutManager(linearLayoutManager);
        mRecyclerViewContacts.setHasFixedSize(true);
    }

    @Override
    public ContactAdapter getAdapter(ArrayList<Contact> contacts) {
        ContactAdapter contactAdapter = new ContactAdapter(contacts, getActivity());
        return contactAdapter;
    }

    @Override
    public void initiateRecyclerViewAdapter(ContactAdapter contactAdapter) {
        mRecyclerViewContacts.setAdapter(contactAdapter);
    }
}
