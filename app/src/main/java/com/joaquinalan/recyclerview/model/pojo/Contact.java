package com.joaquinalan.recyclerview.model.pojo;

/**
 * Created by joaquinalan on 25/01/2017.
 */

public class Contact {
    private int mId;
    private String mName;
    private String mPhone;
    private String mEmail;
    private int mImage;
    private int mNumberOfLikes;

    public Contact(String name, String phone, String email, int image) {
        this.mName = name;
        this.mPhone = phone;
        mEmail = email;
        mImage = image;
    }

    public Contact() {

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        this.mPhone = phone;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        this.mImage = image;
    }

    public int getNumberOfLikes() {
        return mNumberOfLikes;
    }

    public void setNumberOfLikes(int mLikes) {
        this.mNumberOfLikes = mLikes;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }
}
