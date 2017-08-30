package com.joaquinalan.recyclerview.model.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.joaquinalan.recyclerview.model.pojo.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaquinalan on 06/02/2017.
 */

public class ContactsDbHelper extends SQLiteOpenHelper implements ContactsPersistence {
    public static final String DATABASE_NAME = "contacts.db";
    public static final int DATABASE_VERSION = 1;
    private static final int LIKE = 1;
    private SQLiteDatabase mSqLiteDatabase;

    //private Context mContext;

    public ContactsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mSqLiteDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCreateContactTable = "CREATE TABLE " +
                ContactDisplayerContract.ContactTable.TABLE_NAME + "(" +
                ContactDisplayerContract.ContactTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContactDisplayerContract.ContactTable.COLUMN_NAME + " TEXT, " +
                ContactDisplayerContract.ContactTable.COLUMN_PHONE + " TEXT, " +
                ContactDisplayerContract.ContactTable.COLUMN_EMAIL + " TEXT, " +
                ContactDisplayerContract.ContactTable.COLUMN_IMAGE + " INTEGER" +
                ")";

        String queryCreateContactLikesTable = "CREATE TABLE " +
                ContactDisplayerContract.LikeTable.TABLE_NAME + "(" +
                ContactDisplayerContract.LikeTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContactDisplayerContract.LikeTable.COLUMN_CONTACT_ID + " INTEGER, " +
                ContactDisplayerContract.LikeTable.COLUMN_NUMBER_OF_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ContactDisplayerContract.LikeTable.COLUMN_CONTACT_ID + ") " +
                "REFERENCES " + ContactDisplayerContract.ContactTable.TABLE_NAME +
                "(" + ContactDisplayerContract.ContactTable._ID + ")" +
                ")";
        sqLiteDatabase.execSQL(queryCreateContactTable);
        sqLiteDatabase.execSQL(queryCreateContactLikesTable);

        setupDatabase(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + ContactDisplayerContract
                .ContactTable.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + ContactDisplayerContract
                .LikeTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void setupDatabase(SQLiteDatabase sqLiteDatabase) {
        ContentValues contentValues = new ContentValues();

        for (Contact contact : ContactConstructor.getContacts()) {
            contentValues.put(ContactDisplayerContract.ContactTable
                    .COLUMN_NAME, contact.getName());
            contentValues.put(ContactDisplayerContract.ContactTable
                    .COLUMN_PHONE, contact.getPhone());
            contentValues.put(ContactDisplayerContract.ContactTable
                    .COLUMN_EMAIL, contact.getEmail());
            contentValues.put(ContactDisplayerContract.ContactTable
                    .COLUMN_IMAGE, contact.getImage());

            sqLiteDatabase.insert(ContactDisplayerContract.ContactTable.TABLE_NAME, null, contentValues);
            //sqLiteDatabase.close();
        }

    }

    @Override
    public void likeContact(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactDisplayerContract.LikeTable.COLUMN_CONTACT_ID, contact.getId());
        contentValues.put(ContactDisplayerContract.LikeTable.COLUMN_NUMBER_OF_LIKES, LIKE);

        mSqLiteDatabase.insert(ContactDisplayerContract.LikeTable.TABLE_NAME, null, contentValues);
    }

    @Override
    public Iterable<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();

        String query = "SELECT* FROM " + ContactDisplayerContract.ContactTable.TABLE_NAME;
        Cursor records = mSqLiteDatabase.rawQuery(query, null);

        while (records.moveToNext()) {
            Contact currentContact = new Contact();
            currentContact.setId(records.getInt(records
                    .getColumnIndex(ContactDisplayerContract.ContactTable._ID)));
            currentContact.setName(records.getString(records
                    .getColumnIndex(ContactDisplayerContract.ContactTable.COLUMN_NAME)));
            currentContact.setPhone(records.getString(records
                    .getColumnIndex(ContactDisplayerContract.ContactTable.COLUMN_PHONE)));
            currentContact.setEmail(records.getString(records
                    .getColumnIndex(ContactDisplayerContract.ContactTable.COLUMN_EMAIL)));
            currentContact.setImage(records.getInt(records
                    .getColumnIndex(ContactDisplayerContract.ContactTable.COLUMN_IMAGE)));

            String queryLikes = "SELECT COUNT (" +
                    ContactDisplayerContract.LikeTable.COLUMN_NUMBER_OF_LIKES + ") AS LIKES " +
                    "FROM " + ContactDisplayerContract.LikeTable.TABLE_NAME +
                    " WHERE " + ContactDisplayerContract.LikeTable.COLUMN_CONTACT_ID + "=" +
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
        String query = "SELECT COUNT (" + ContactDisplayerContract.LikeTable.COLUMN_NUMBER_OF_LIKES + ")" +
                " FROM " + ContactDisplayerContract.LikeTable.TABLE_NAME +
                " WHERE " + ContactDisplayerContract.LikeTable.COLUMN_CONTACT_ID + "=" + contact.getId();

        Cursor records = mSqLiteDatabase.rawQuery(query, null);

        if (records.moveToNext()) {
            likes = records.getInt(0);
        }
        //mSqLiteDatabase.close();
        return likes;
    }

    @Override
    public Contact getContact(int contactId) {
        Cursor contactCursor = mSqLiteDatabase.query(
                ContactDisplayerContract.ContactTable.TABLE_NAME,
                null,
                contactId + " = " + ContactDisplayerContract.ContactTable._ID,
                null,
                null,
                null,
                null);

        if (contactCursor.moveToNext()) {
            String name = contactCursor.getString(contactCursor
                    .getColumnIndex(ContactDisplayerContract.ContactTable.COLUMN_NAME));
            String phone = contactCursor.getString(contactCursor
                    .getColumnIndex(ContactDisplayerContract.ContactTable.COLUMN_PHONE));
            String email = contactCursor.getString(contactCursor
                    .getColumnIndex(ContactDisplayerContract.ContactTable.COLUMN_EMAIL));
            int image = contactCursor.getInt(contactCursor
                    .getColumnIndex(ContactDisplayerContract.ContactTable.COLUMN_IMAGE));
            int id = contactCursor.getInt(contactCursor
                    .getColumnIndex(ContactDisplayerContract.ContactTable._ID));

            Contact contact = new Contact(
                    name,
                    phone,
                    email,
                    image
            );
            contact.setId(id);

            Cursor likesCursor = mSqLiteDatabase.query(
                    ContactDisplayerContract.LikeTable.TABLE_NAME,
                    new String[]{"COUNT(" + ContactDisplayerContract.LikeTable._ID + ") AS count"},
                    ContactDisplayerContract.LikeTable.COLUMN_CONTACT_ID + " = " + contactId,
                    null,
                    null,
                    null,
                    null);

            if (likesCursor.moveToNext()) {
                int numberOfLikes = likesCursor.getInt(0);
                contact.setNumberOfLikes(numberOfLikes);
            }

            return contact;
        }

        return null;
    }
}
