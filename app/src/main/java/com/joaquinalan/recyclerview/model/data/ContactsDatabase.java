package com.joaquinalan.recyclerview.model.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joaquinalan.recyclerview.model.pojo.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaquinalan on 28/08/2017.
 */

public class ContactsDatabase implements ContactsDatabaseInterface {
    private SQLiteDatabase mSqLiteDatabase;
    private static final int LIKE = 1;

    public ContactsDatabase(SQLiteDatabase sqLiteDatabase) {
        mSqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public void likeContact(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactDisplayerContract.LikesEntry.COLUMN_CONTACT_ID, contact.getId());
        contentValues.put(ContactDisplayerContract.LikesEntry.COLUMN_NUMBER_OF_LIKES, LIKE);

        mSqLiteDatabase.insert(ContactDisplayerContract.LikesEntry.TABLE_NAME, null, contentValues);
    }

    @Override
    public Iterable<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();

        String query = "SELECT* FROM " + ContactDisplayerContract.ContactsEntry.TABLE_NAME;
        Cursor records = mSqLiteDatabase.rawQuery(query, null);

        while (records.moveToNext()) {
            Contact currentContact = new Contact();
            currentContact.setId(records.getInt(records
                    .getColumnIndex(ContactDisplayerContract.ContactsEntry._ID)));
            currentContact.setName(records.getString(records
                    .getColumnIndex(ContactDisplayerContract.ContactsEntry.COLUMN_NAME)));
            currentContact.setPhone(records.getString(records
                    .getColumnIndex(ContactDisplayerContract.ContactsEntry.COLUMN_PHONE)));
            currentContact.setEmail(records.getString(records
                    .getColumnIndex(ContactDisplayerContract.ContactsEntry.COLUMN_EMAIL)));
            currentContact.setImage(records.getInt(records
                    .getColumnIndex(ContactDisplayerContract.ContactsEntry.COLUMN_IMAGE)));
//            currentContact.setId(records.getInt(0));
//            currentContact.setName(records.getString(1));
//            currentContact.setPhone(records.getString(2));
//            currentContact.setEmail(records.getString(3));
//            currentContact.setImage(records.getInt(4));

            String queryLikes = "SELECT COUNT (" +
                    ContactDisplayerContract.LikesEntry.COLUMN_NUMBER_OF_LIKES + ") AS LIKES " +
                    "FROM " + ContactDisplayerContract.LikesEntry.TABLE_NAME +
                    " WHERE " + ContactDisplayerContract.LikesEntry.COLUMN_CONTACT_ID + "=" +
                    currentContact.getId();
            Cursor likesRecords = mSqLiteDatabase.rawQuery(queryLikes, null);

            if (likesRecords.moveToNext()) {
                currentContact.setNumberOfLikes(likesRecords.getInt(0));
            } else {
                currentContact.setNumberOfLikes(0);
            }

            contacts.add(currentContact);
        }
        //mSqLiteDatabase.close();
        return contacts;
    }

    @Override
    public int getContactLikes(Contact contact) {
        int likes = 0;
        String query = "SELECT COUNT (" + ContactDisplayerContract.LikesEntry.COLUMN_NUMBER_OF_LIKES + ")" +
                " FROM " + ContactDisplayerContract.LikesEntry.TABLE_NAME +
                " WHERE " + ContactDisplayerContract.LikesEntry.COLUMN_CONTACT_ID + "=" + contact.getId();

        Cursor records = mSqLiteDatabase.rawQuery(query, null);

        if (records.moveToNext()) {
            likes = records.getInt(0);
        }
        //mSqLiteDatabase.close();
        return likes;
    }
}
