package com.joaquinalan.recyclerview.data;

import android.content.ContentValues;
import android.content.Context;

import com.joaquinalan.recyclerview.R;
import com.joaquinalan.recyclerview.pojo.Contact;

import java.util.ArrayList;

/**
 * Created by joaquinalan on 05/02/2017.
 */

public class ContactConstructor {
    private static final int LIKE = 1;
    private Context mContext;
    private ArrayList<Contact> mContacts;

    public ContactConstructor(Context context) {
        this.mContext = context;
    }

    public ArrayList<Contact> getContacts() {
        DataBase dataBase = new DataBase(mContext);
        insertThreeContacts(dataBase);
        return dataBase.getContacts();
    }

    public void insertThreeContacts(DataBase dataBase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_CONTACTS_NAME, "Joaquín Alvidrez");
        contentValues.put(DataBaseConstants.TABLE_CONTACTS_PHONE, "6666666");
        contentValues.put(DataBaseConstants.TABLE_CONTACTS_EMAIL, "joaquin_alan@hotmail.com");
        contentValues.put(DataBaseConstants.TABLE_CONTACTS_IMAGE, R.drawable.florence_and_the_machine_logo);
        dataBase.insertContact(contentValues);

        contentValues.put(DataBaseConstants.TABLE_CONTACTS_NAME, "Osama Bin Laden");
        contentValues.put(DataBaseConstants.TABLE_CONTACTS_PHONE, "83396825");
        contentValues.put(DataBaseConstants.TABLE_CONTACTS_EMAIL, "osama.laden@hotmail.com");
        contentValues.put(DataBaseConstants.TABLE_CONTACTS_IMAGE, R.drawable.led_zeppelin_logo);
        dataBase.insertContact(contentValues);

        contentValues.put(DataBaseConstants.TABLE_CONTACTS_NAME, "Peña Nieto");
        contentValues.put(DataBaseConstants.TABLE_CONTACTS_PHONE, "111111");
        contentValues.put(DataBaseConstants.TABLE_CONTACTS_EMAIL, "peña_nieto@hotmail.com");
        contentValues.put(DataBaseConstants.TABLE_CONTACTS_IMAGE, R.drawable.florence_and_the_machine_logo);
        dataBase.insertContact(contentValues);

        contentValues.put(DataBaseConstants.TABLE_CONTACTS_NAME, "Donald Trump");
        contentValues.put(DataBaseConstants.TABLE_CONTACTS_PHONE, "67678786");
        contentValues.put(DataBaseConstants.TABLE_CONTACTS_EMAIL, "popo@hotmail.com");
        contentValues.put(DataBaseConstants.TABLE_CONTACTS_IMAGE, R.drawable.beatles_logo);
        dataBase.insertContact(contentValues);
    }

    public void likeContact(Contact contact) {
        DataBase dataBase = new DataBase(mContext);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_CONTACT_LIKES_CONTACT_ID, contact.getId());
        contentValues.put(DataBaseConstants.TABLE_CONTACT_LIKES_NUMBER_OF_LIKES, LIKE);
        dataBase.insertContactLike(contentValues);
    }

    public int getContactLikes(Contact contact) {
        DataBase dataBase = new DataBase(mContext);
        return dataBase.getContactLikes(contact);
    }
}
