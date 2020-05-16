package com.example.infi_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.infi_project.data.ChatTab;
import com.example.infi_project.data.ExploreTab;
import com.example.infi_project.data.FeedTab;
import com.example.infi_project.data.ProfileTab;
import com.google.android.material.tabs.TabLayout;



public class AppMainPage extends AppCompatActivity {


    public TabLayout tabLayout;
    public ViewPager viewPager;
    public Toolbar toolbar;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main_page);

        toolbar=findViewById(R.id.myToolBar);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.pager);
        pagerAdapter=new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Infi");


        tabLayout.setupWithViewPager(viewPager);
//        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
//        tabLayout.addTab(tabLayout.newTab().setText("Feed"));
//        tabLayout.addTab(tabLayout.newTab().setText("Chat"));
//        tabLayout.addTab(tabLayout.newTab().setText("Explore"));
//        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final ViewPager viewPager=(ViewPager)findViewById(R.id.pager);
//        final PagerAdapter adapter= new PagerAdapter (getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
}
