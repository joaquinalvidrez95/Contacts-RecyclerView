package com.joaquinalan.recyclerview.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.joaquinalan.recyclerview.R;
import com.joaquinalan.recyclerview.model.ContactsDisplayer;
import com.joaquinalan.recyclerview.model.ContactsDisplayerModel;
import com.joaquinalan.recyclerview.model.data.ContactsDatabase;
import com.joaquinalan.recyclerview.model.data.ContactsDatabaseInterface;
import com.joaquinalan.recyclerview.model.data.ContactsDbHelper;
import com.joaquinalan.recyclerview.model.pojo.Contact;
import com.joaquinalan.recyclerview.presenter.ContactDisplayerFragmentListener;
import com.joaquinalan.recyclerview.presenter.ContactDisplayerFragmentPresenter;
import com.joaquinalan.recyclerview.view.activity.ContactDetailActivity;
import com.joaquinalan.recyclerview.view.adapter.ContactAdapter;

/**
 * Created by joaquinalan on 29/01/2017.
 */

public class ContactDisplayerFragment extends Fragment implements ContactDisplayerView, ContactAdapter.ContactAdapterListener {
    private RecyclerView mRecyclerViewContacts;
    private ContactDisplayerFragmentListener mPresenter;
    private ContactAdapter mContactAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_contactdisplayer, container, false);

        mRecyclerViewContacts = (RecyclerView) view.findViewById(R.id.recyclerview_recyclerview_contacts);

        setupMVP();

        return view;
    }

    private void setupMVP() {
        SQLiteOpenHelper db = new ContactsDbHelper(getContext());
        ContactsDisplayerModel contactsDisplayerModel;
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContactsDatabaseInterface contactsDatabase = new ContactsDatabase(sqLiteDatabase);
        contactsDisplayerModel = new ContactsDisplayer(contactsDatabase);
        mPresenter = new ContactDisplayerFragmentPresenter(this, contactsDisplayerModel);
    }

    @Override
    public void createVerticalLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewContacts.setLayoutManager(linearLayoutManager);
        mRecyclerViewContacts.setHasFixedSize(true);
    }

//    @Override
//    public ContactAdapter getAdapter(Iterable<Contact> contacts) {
//        ContactAdapter contactAdapter = new ContactAdapter(contacts, this);
//        mContactAdapter = contactAdapter;
//        return contactAdapter;
//    }

    @Override
    public void initiateRecyclerViewAdapter(Iterable<Contact> contacts) {
        mContactAdapter = new ContactAdapter(contacts, this);
        mRecyclerViewContacts.setAdapter(mContactAdapter);
    }

    @Override
    public void startContactDetailActivicty(Contact contact) {
        //Snackbar.make(view, contact.getName(), Snackbar.LENGTH_SHORT).show();
        Activity activity = getActivity();
        Intent intent = new Intent(activity, ContactDetailActivity.class);
        intent.putExtra(activity.getString(R.string.contactadapter_extracontactname),
                contact.getName());
        intent.putExtra(activity.getString(R.string.contactadapter_extracontactphone),
                contact.getPhone());
        intent.putExtra(activity.getString(R.string.contactadapter_extracontactemail),
                contact.getEmail());
        activity.startActivity(intent);
    }

    @Override
    public void showContactClicked(Contact contact) {
        Toast.makeText(getContext(), contact.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContactLiked(Contact contact) {
        Toast.makeText(getContext(), getString(R.string.contactcardview_thumbupmessage) +
                contact.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setContacts(Iterable<Contact> contacts) {
        mContactAdapter.updateContacts(contacts);
    }

    @Override
    public void onContactCardViewClicked(Contact contact) {
        mPresenter.onContactCardViewClicked(contact);
    }

    @Override
    public void onThumbUpClicked(Contact contact) {
        mPresenter.onThumbUpClicked(contact);
    }
}
