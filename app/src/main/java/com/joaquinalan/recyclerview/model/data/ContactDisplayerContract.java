package com.joaquinalan.recyclerview.model.data;

import android.provider.BaseColumns;

/**
 * Created by joaquinalan on 06/02/2017.
 */

class ContactDisplayerContract {
    static final class ContactsEntry implements BaseColumns {
        static final String TABLE_NAME = "contact";

        //public static final String TABLE_CONTACTS = "contact";
        static final String COLUMN_NAME = "name";
        static final String COLUMN_PHONE = "phone";
        static final String COLUMN_EMAIL = "email";
        static final String COLUMN_IMAGE = "image";
    }

    static final class LikesEntry implements BaseColumns {
        static final String TABLE_NAME = "like";

        //public static final String TABLE_CONTACT_LIKES = "contactLikes";
        static final String COLUMN_CONTACT_ID = "contactId";
        static final String COLUMN_NUMBER_OF_LIKES = "numberOfLikes";
    }
}
