package com.joaquinalan.recyclerview.model;

import com.joaquinalan.recyclerview.model.pojo.Contact;

/**
 * Created by joaquinalan on 29/08/2017.
 */

public interface ContactsDisplayerListener {
    void onNumberOfLikesChanged(Contact contact);
}
