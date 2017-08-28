package com.joaquinalan.recyclerview.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.joaquinalan.recyclerview.R;
import com.joaquinalan.recyclerview.activities.ContactDetailActivity;
import com.joaquinalan.recyclerview.data.ContactConstructor;
import com.joaquinalan.recyclerview.pojo.Contact;

import java.util.ArrayList;

/**
 * Created by joaquinalan on 26/01/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private ArrayList<Contact> mContacts;
    private Activity mActivity;

    public ContactAdapter(ArrayList<Contact> contacts, Activity activity) {
        this.mContacts = contacts;
        this.mActivity = activity;
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

    /*    holder.mImageViewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, mContact.getName(), Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(mActivity, ContactDetailActivity.class);
                intent.putExtra(mActivity.getString(R.string.contactadapter_extracontactname), mContact.getName());
                intent.putExtra(mActivity.getString(R.string.contactadapter_extracontactphone), mContact.getPhone());
                intent.putExtra(mActivity.getString(R.string.contactadapter_extracontactemail), mContact.getEmail());
                mActivity.startActivity(intent);
            }
        });

        holder.mButtonThumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, mActivity.getString(R.string.contactcardview_thumbupmessage)
                        + mContact.getName(), Snackbar.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public int getItemCount() { // Number of elements that my List has.
        return mContacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView mImageViewImage;
        private final TextView mTextViewName;
        private final TextView mTextViewPhone;
        private final ImageButton mButtonThumbUp;
        private final TextView mTextViewNumberOfLikes;

        public ContactViewHolder(View itemView) {
            super(itemView);
            mImageViewImage = (ImageView) itemView.findViewById(R.id.imageview_contactcardview_image);
            mTextViewName = (TextView) itemView.findViewById(R.id.textview_contactcardview_name);
            mTextViewPhone = (TextView) itemView.findViewById(R.id.textview_contactcardview_phone);
            mTextViewNumberOfLikes = (TextView) itemView.findViewById(R.id.textview_contactcardview_numberoflikes);
            mButtonThumbUp = (ImageButton) itemView.findViewById(R.id.button_contactcardview_thumbup);

            mImageViewImage.setOnClickListener(this);
            mButtonThumbUp.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int contactPosition = getAdapterPosition();
            final Contact mContact = mContacts.get(contactPosition);
            switch (view.getId()) {
                case R.id.imageview_contactcardview_image:
                    Snackbar.make(view, mContact.getName(), Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(mActivity, ContactDetailActivity.class);
                    intent.putExtra(mActivity.getString(R.string.contactadapter_extracontactname), mContact.getName());
                    intent.putExtra(mActivity.getString(R.string.contactadapter_extracontactphone), mContact.getPhone());
                    intent.putExtra(mActivity.getString(R.string.contactadapter_extracontactemail), mContact.getEmail());
                    mActivity.startActivity(intent);
                    break;
                case R.id.button_contactcardview_thumbup:
                    Snackbar.make(view, mActivity.getString(R.string.contactcardview_thumbupmessage)
                            + mContact.getName(), Snackbar.LENGTH_SHORT).show();
                    ContactConstructor contactConstructor = new ContactConstructor(mActivity);
                    contactConstructor.likeContact(mContact);
                    int numberLikes = contactConstructor.getContactLikes(mContact);
                    mTextViewNumberOfLikes.setText(String.valueOf(numberLikes));
                    break;
            }
        }
    }
}
