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

public class ContactsDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "contacts.db";
    public static final int DATABASE_VERSION = 1;

    //private Context mContext;

    public ContactsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCreateContactTable = "CREATE TABLE " +
                ContactDisplayerContract.ContactsEntry.TABLE_NAME + "(" +
                ContactDisplayerContract.ContactsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContactDisplayerContract.ContactsEntry.COLUMN_NAME + " TEXT, " +
                ContactDisplayerContract.ContactsEntry.COLUMN_PHONE + " TEXT, " +
                ContactDisplayerContract.ContactsEntry.COLUMN_EMAIL + " TEXT, " +
                ContactDisplayerContract.ContactsEntry.COLUMN_IMAGE + " INTEGER" +
                ")";

        String queryCreateContactLikesTable = "CREATE TABLE " +
                ContactDisplayerContract.LikesEntry.TABLE_NAME + "(" +
                ContactDisplayerContract.LikesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContactDisplayerContract.LikesEntry.COLUMN_CONTACT_ID + " INTEGER, " +
                ContactDisplayerContract.LikesEntry.COLUMN_NUMBER_OF_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ContactDisplayerContract.LikesEntry.COLUMN_CONTACT_ID + ") " +
                "REFERENCES " + ContactDisplayerContract.ContactsEntry.TABLE_NAME +
                "(" + ContactDisplayerContract.ContactsEntry._ID + ")" +
                ")";
        sqLiteDatabase.execSQL(queryCreateContactTable);
        sqLiteDatabase.execSQL(queryCreateContactLikesTable);

        setupDatabase(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + ContactDisplayerContract.ContactsEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + ContactDisplayerContract.LikesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void setupDatabase(SQLiteDatabase sqLiteDatabase) {
        ContentValues contentValues = new ContentValues();

        for (Contact contact : ContactConstructor.getContacts()) {
            contentValues.put(ContactDisplayerContract.ContactsEntry
                    .COLUMN_NAME, contact.getName());
            contentValues.put(ContactDisplayerContract.ContactsEntry
                    .COLUMN_PHONE, contact.getPhone());
            contentValues.put(ContactDisplayerContract.ContactsEntry
                    .COLUMN_EMAIL, contact.getEmail());
            contentValues.put(ContactDisplayerContract.ContactsEntry
                    .COLUMN_IMAGE, contact.getImage());

            sqLiteDatabase.insert(ContactDisplayerContract.ContactsEntry.TABLE_NAME, null, contentValues);
            //sqLiteDatabase.close();
        }

    }
}
