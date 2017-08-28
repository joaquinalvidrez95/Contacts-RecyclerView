package com.joaquinalan.recyclerview.data;

/**
 * Created by joaquinalan on 06/02/2017.
 */

public final class DataBaseConstants {
    public static final String DATABASE_NAME = "Contacts";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_CONTACTS = "Contact";
    public static final String TABLE_CONTACTS_ID = "Id";
    public static final String TABLE_CONTACTS_NAME = "Name";
    public static final String TABLE_CONTACTS_PHONE = "Phone";
    public static final String TABLE_CONTACTS_EMAIL = "Email";
    public static final String TABLE_CONTACTS_IMAGE = "Image";

    public static final String TABLE_CONTACT_LIKES = "contactLikes";
    public static final String TABLE_CONTACT_LIKES_ID = "Id";
    public static final String TABLE_CONTACT_LIKES_CONTACT_ID = "ContactId";
    public static final String TABLE_CONTACT_LIKES_NUMBER_OF_LIKES = "NumberOfLikes";
}
