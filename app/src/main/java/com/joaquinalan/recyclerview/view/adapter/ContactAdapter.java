package com.joaquinalan.recyclerview.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.joaquinalan.recyclerview.R;
import com.joaquinalan.recyclerview.model.pojo.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by joaquinalan on 26/01/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> mContacts = new ArrayList<>();
    private ContactAdapterListener mContactAdapterListener;

    public ContactAdapter(Iterable<Contact> contacts, ContactAdapterListener contactAdapterListener) {
        for (final Contact contact : contacts) {
            mContacts.add(contact);
        }

        this.mContactAdapterListener = contactAdapterListener;
    }

    // It inflates layout and passes viewHolder to get the views
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.view_contactadapter_contactcardview, parent, false);
        return new ContactViewHolder(view);
    }

    // Matches each element from the list each view
    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {
        final Contact mContact = mContacts.get(position);

        holder.mImageViewImage.setImageResource(mContact.getImage());
        holder.mTextViewName.setText(mContact.getName());
        holder.mTextViewPhone.setText(mContact.getPhone());
        holder.mTextViewNumberOfLikes.setText(String.valueOf(mContact.getNumberOfLikes()));
    }

    @Override
    public int getItemCount() { // Number of elements that my List has.
        return mContacts.size();
    }

    public void updateContacts(Iterable<Contact> contacts) {
        mContacts.clear();

        for (final Contact contact : contacts) {
            mContacts.add(contact);
        }
        notifyDataSetChanged();
    }

    public void setContact(Contact newContact) {
//        for (final Contact contact : mContacts) {
//            if (contact.getId() == newContact.getId()) {
//                contact.setNumberOfLikes(newContact.getNumberOfLikes());
//            }
//        }

        ListIterator<Contact> listIterator = mContacts.listIterator();
        while (listIterator.hasNext()) {
            // Need to call next, before set.

            if (listIterator.next().getId() == newContact.getId()) {
                // Replace item returned from next()
                listIterator.set(newContact);
            }
        }
        notifyDataSetChanged();
    }

    public interface ContactAdapterListener {
        void onContactCardViewClicked(Contact contact);

        void onThumbUpClicked(Contact contact);
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView mImageViewImage;
        private final TextView mTextViewName;
        private final TextView mTextViewPhone;
        private final ImageButton mButtonThumbUp;
        private final TextView mTextViewNumberOfLikes;

        public ContactViewHolder(View itemView) {
            super(itemView);
            mImageViewImage = (ImageView) itemView.findViewById(R.id
                    .imageview_contactcardview_image);
            mTextViewName = (TextView) itemView.findViewById(R.id
                    .textview_contactcardview_name);
            mTextViewPhone = (TextView) itemView.findViewById(R.id
                    .textview_contactcardview_phone);
            mTextViewNumberOfLikes = (TextView) itemView.findViewById(R.id
                    .textview_contactcardview_numberoflikes);
            mButtonThumbUp = (ImageButton) itemView.findViewById(R.id
                    .button_contactcardview_thumbup);

            mImageViewImage.setOnClickListener(this);
            mButtonThumbUp.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int contactPosition = getAdapterPosition();
            final Contact contact = mContacts.get(contactPosition);
            switch (view.getId()) {
                case R.id.imageview_contactcardview_image:
                    mContactAdapterListener.onContactCardViewClicked(contact);
                    break;
                case R.id.button_contactcardview_thumbup:
                    mContactAdapterListener.onThumbUpClicked(contact);
                    break;
            }
        }
    }
}
