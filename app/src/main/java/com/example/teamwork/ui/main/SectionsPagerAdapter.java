package com.example.teamwork.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teamwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

   ArrayList<Fragment> fragmentList=new ArrayList<>();
  final ArrayList<String> fragmentTitle=new ArrayList<>();

   public void addFragment(Fragment fragment,String title) {
       fragmentList.add(fragment);
       fragmentTitle.add(title);
   }

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
    return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
      return fragmentTitle.get(position);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return fragmentList.size();
    }
}