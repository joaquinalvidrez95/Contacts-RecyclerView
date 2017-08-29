package com.joaquinalan.recyclerview.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.joaquinalan.recyclerview.R;
import com.joaquinalan.recyclerview.view.adapter.PageAdapter;
import com.joaquinalan.recyclerview.view.fragment.ContactDisplayerFragment;
import com.joaquinalan.recyclerview.view.fragment.ProfileFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar tb;
    private TabLayout tl;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tbMyActionBar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(tbMyActionBar);

        tb = (Toolbar) findViewById(R.id.toolbar_main);
        tl = (TabLayout) findViewById(R.id.tablayout_main);
        vp = (ViewPager) findViewById(R.id.viewpager_main);

        setupViewPager();

        if (tb != null) {
            setSupportActionBar(tb);
        }
    }

    private ArrayList<Fragment> addFragments() {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

        fragmentArrayList.add(new ContactDisplayerFragment());
        fragmentArrayList.add(new ProfileFragment());
        return fragmentArrayList;
    }

    private void setupViewPager() {
        vp.setAdapter(new PageAdapter(getSupportFragmentManager(), addFragments()));
        tl.setupWithViewPager(vp);
        tl.getTabAt(0).setIcon(R.drawable.ic_contacts);
        tl.getTabAt(1).setIcon(R.drawable.ic_profile);
    }
}
