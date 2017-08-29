package com.joaquinalan.recyclerview.presenter;

import com.joaquinalan.recyclerview.model.pojo.Contact;

/**
 * Created by joaquinalan on 04/02/2017.
 */

public interface ContactDisplayerFragmentListener {

    void onContactCardViewClicked(Contact contact);

    void onThumbUpClicked(Contact contact);
}
