package com.joaquinalan.recyclerview.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.joaquinalan.recyclerview.R;

public class ContactDetailActivity extends AppCompatActivity {
    private TextView mTextViewName;
    private TextView mTextViewPhone;
    private TextView mTextViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactdetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_contactdetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.contactdetail_toolbartitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextViewName = (TextView) findViewById(R.id.textview_contactdetail_name);
        mTextViewPhone = (TextView) findViewById(R.id.textview_contactdetail_phone);
        mTextViewEmail = (TextView) findViewById(R.id.textview_contactdetail_email);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mTextViewName.setText(extras.getString(getString(R.string.contactadapter_extracontactname)));
            mTextViewPhone.setText(extras.getString(getString(R.string.contactadapter_extracontactphone)));
            mTextViewEmail.setText(extras.getString(getString(R.string.contactadapter_extracontactemail)));
        }
    }

    public void call(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mTextViewPhone.getText().toString())));
    }

    public void sendEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mTextViewEmail.getText().toString());
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "E-mail"));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ContactDetailActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
