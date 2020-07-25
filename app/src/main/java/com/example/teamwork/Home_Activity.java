package com.example.teamwork;

import android.content.Intent;
import android.os.Bundle;

import com.example.teamwork.Fragments.Contact_fragment;
import com.example.teamwork.Fragments.Images_fragment;
import com.example.teamwork.Fragments.ViewImages_fragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamwork.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home_Activity<sectionsPagerAdapter> extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView menu;
    FirebaseAuth mauth;
    ActionBarDrawerToggle mtoggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        mauth=FirebaseAuth.getInstance();
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         navigationView=(NavigationView)findViewById(R.id.naV);
         navigationView.setNavigationItemSelectedListener(this);
         drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
         navigationView.setNavigationItemSelectedListener(this);
         mtoggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        String user=mauth.getCurrentUser().getEmail();
        updateHeader(user);

        //setSupportActionBar(toolbar);
         drawerLayout.addDrawerListener(mtoggle);
         mtoggle.setDrawerIndicatorEnabled(true);
         mtoggle.syncState();
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        setupViewpager(viewPager);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void updateHeader(String user) {
        NavigationView navigationView=(NavigationView)findViewById(R.id.naV);
        View headerview=navigationView.getHeaderView(0);
        TextView navmail=headerview.findViewById(R.id.navMail);
        navmail.setText(user);
    }

    private void setupViewpager(ViewPager viewPager){
        SectionsPagerAdapter newsectionsPagerAdapter=new SectionsPagerAdapter(getSupportFragmentManager());
        newsectionsPagerAdapter.addFragment(new Contact_fragment(),"Contact us");
        newsectionsPagerAdapter.addFragment(new Images_fragment(),"Images");
        newsectionsPagerAdapter.addFragment(new ViewImages_fragment(),"View");
        viewPager.setAdapter(newsectionsPagerAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        if(id==R.id.logout){
            Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_SHORT).show();
            mauth.signOut();
            Intent intent=new Intent(Home_Activity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return true;
    }
}