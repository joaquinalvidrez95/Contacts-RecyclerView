package com.joaquinalan.recyclerview.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.joaquinalan.recyclerview.pojo.Contact;

import java.util.ArrayList;

/**
 * Created by joaquinalan on 06/02/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    private Context mContext;

    public DataBase(Context context) {
        super(context, DataBaseConstants.DATABASE_NAME, null, DataBaseConstants.DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCreateContactTable = "CREATE TABLE " + DataBaseConstants.TABLE_CONTACTS + "(" +
                DataBaseConstants.TABLE_CONTACTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataBaseConstants.TABLE_CONTACTS_NAME + " TEXT, " +
                DataBaseConstants.TABLE_CONTACTS_PHONE + " TEXT, " +
                DataBaseConstants.TABLE_CONTACTS_EMAIL + " TEXT, " +
                DataBaseConstants.TABLE_CONTACTS_IMAGE + " INTEGER" +
                ")";

        String queryCreateContactLikesTable = "CREATE TABLE " + DataBaseConstants.TABLE_CONTACT_LIKES + "(" +
                DataBaseConstants.TABLE_CONTACT_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataBaseConstants.TABLE_CONTACT_LIKES_CONTACT_ID + " INTEGER, " +
                DataBaseConstants.TABLE_CONTACT_LIKES_NUMBER_OF_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + DataBaseConstants.TABLE_CONTACT_LIKES_CONTACT_ID + ") " +
                "REFERENCES " + DataBaseConstants.TABLE_CONTACTS + "(" + DataBaseConstants.TABLE_CONTACTS_ID + ")" +
                ")";
        sqLiteDatabase.execSQL(queryCreateContactTable);
        sqLiteDatabase.execSQL(queryCreateContactLikesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + DataBaseConstants.TABLE_CONTACTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + DataBaseConstants.TABLE_CONTACT_LIKES);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();

        String query = "SELECT* FROM " + DataBaseConstants.TABLE_CONTACTS;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor records = sqLiteDatabase.rawQuery(query, null);

        while (records.moveToNext()) {
            Contact currentContact = new Contact();
            currentContact.setId(records.getInt(0));
            currentContact.setName(records.getString(1));
            currentContact.setPhone(records.getString(2));
            currentContact.setEmail(records.getString(3));
            currentContact.setImage(records.getInt(4));

            String queryLikes = "SELECT COUNT (" + DataBaseConstants.TABLE_CONTACT_LIKES_NUMBER_OF_LIKES + ") AS LIKES " +
                    "FROM " + DataBaseConstants.TABLE_CONTACT_LIKES +
                    " WHERE " + DataBaseConstants.TABLE_CONTACT_LIKES_CONTACT_ID + "=" + currentContact.getId();
            Cursor likesRecords = sqLiteDatabase.rawQuery(queryLikes, null);

            if (likesRecords.moveToNext()) {
                currentContact.setNumberOfLikes(likesRecords.getInt(0));
            } else {
                currentContact.setNumberOfLikes(0);
            }

            contacts.add(currentContact);
        }
        sqLiteDatabase.close();
        return contacts;
    }

    public void insertContact(ContentValues contentValues) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DataBaseConstants.TABLE_CONTACTS, null, contentValues);
        sqLiteDatabase.close();
    }

    public void insertContactLike(ContentValues contentValues) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DataBaseConstants.TABLE_CONTACT_LIKES, null, contentValues);
        sqLiteDatabase.close();
    }

    public int getContactLikes(Contact contact) {
        int likes = 0;
        String query = "SELECT COUNT (" + DataBaseConstants.TABLE_CONTACT_LIKES_NUMBER_OF_LIKES + ")" +
                " FROM " + DataBaseConstants.TABLE_CONTACT_LIKES +
                " WHERE " + DataBaseConstants.TABLE_CONTACT_LIKES_CONTACT_ID + "=" + contact.getId();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor records = sqLiteDatabase.rawQuery(query, null);

        if (records.moveToNext()) {
            likes = records.getInt(0);
        }
        sqLiteDatabase.close();
        return likes;
    }
}
